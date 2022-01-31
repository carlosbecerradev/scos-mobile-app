package com.example.scos_mobile_app.ui.cliente

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.scos_mobile_app.data.network.NuevaOrdenApi
import com.example.scos_mobile_app.data.network.Resource
import com.example.scos_mobile_app.data.repository.NuevaOrdenRepository
import com.example.scos_mobile_app.data.responses.ClienteX
import com.example.scos_mobile_app.data.responses.OrdenDeServicio
import com.example.scos_mobile_app.data.responses.TipoDeIncidenciaX
import com.example.scos_mobile_app.databinding.FragmentNuevaOrdenBinding
import com.example.scos_mobile_app.ui.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.util.ArrayList


class NuevaOrdenFragment : BaseFragment<NuevaOrdenViewModel, FragmentNuevaOrdenBinding, NuevaOrdenRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val spinner = binding.spinnerTiposDeIncidencia
        var tiposDeIncidenciasIds = ArrayList<Int>()
        var tiposDeIncidenciaNames = ArrayList<String>()
        //---- Nueva Orden
        var tipoDeIncidenciaId: Int = 0
        var clienteId = runBlocking { userPreferences.clienteId.first() }
        var tipoDeServicioNombre = runBlocking { userPreferences.tipoDeServicioNombre.first() }

        if (tipoDeServicioNombre != null) {
            viewModel.getTiposdDeIncidencia(tipoDeServicioNombre)
        }
        viewModel.tiposDeIncidencia.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    Log.e("--//-->", it.value.toString())
                    it.value.forEach {
                        tiposDeIncidenciasIds.add(it.tipoDeIncidenciaId)
                        tiposDeIncidenciaNames.add(it.nombre)
                    }
                    val adaptador = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, tiposDeIncidenciaNames)
                    spinner.adapter = adaptador
                }
            }
        })

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                tipoDeIncidenciaId = tiposDeIncidenciasIds.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        var clienteX = clienteId?.let { it1 -> ClienteX(it1.toInt()) }

        binding.btnGenerarOrden.setOnClickListener {
            var descripcion = binding.edtDescripcionIncidencia.text.toString()
            var tipoDeIncidenciaX = TipoDeIncidenciaX(tipoDeIncidenciaId)
            var nuevaOrden: OrdenDeServicio = OrdenDeServicio(clienteX,descripcion,
                null, "CREADA", null, null,null,
                null,null,null,null, null,
                null,false, tipoDeIncidenciaX)
            Log.i("btn -->", nuevaOrden.toString())
            viewModel.createOrdenDeServicio(nuevaOrden)
            // binding.btnGenerarOrden.isEnabled = false
        }
    }

    override fun getViewModel(): Class<NuevaOrdenViewModel> = NuevaOrdenViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNuevaOrdenBinding = FragmentNuevaOrdenBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): NuevaOrdenRepository {
        var token = runBlocking { userPreferences.authToken.first() }
        var api = remoteDataSource.buildApi(NuevaOrdenApi::class.java, token)
        return NuevaOrdenRepository(api)
    }

}