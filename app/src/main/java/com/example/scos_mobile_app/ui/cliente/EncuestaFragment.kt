package com.example.scos_mobile_app.ui.cliente

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scos_mobile_app.data.network.EncuestaApi
import com.example.scos_mobile_app.data.network.Resource
import com.example.scos_mobile_app.data.repository.EncuestaRepository
import com.example.scos_mobile_app.data.responses.EncuestaDto
import com.example.scos_mobile_app.data.responses.OrdenDeServicioDto
import com.example.scos_mobile_app.databinding.FragmentEncuestaBinding
import com.example.scos_mobile_app.ui.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class EncuestaFragment : BaseFragment<EncuestaViewModel, FragmentEncuestaBinding, EncuestaRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iniciarUI()
        var clienteId = runBlocking { userPreferences.clienteId.first() }
        if (clienteId != null) {
            viewModel.getUltimaOrden(clienteId.toLong())
        }
        var ordenId = 0
        viewModel.ordenDeServicioDto.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    var orden: OrdenDeServicioDto = it.value
                    ordenId = orden.id!!
                    if(orden.estado.equals("RESUELTA")) {
                        binding.lyEncuestaForm.visibility = View.VISIBLE
                        binding.lyEncuestaMensaje.visibility = View.GONE
                    } else {
                        iniciarUI()
                    }
                }
            }
        })

        binding.btnEncuestaEnviar.setOnClickListener {
            var valoracion = seleccionarValoracion()
            var observacion = binding.edtEncuestaObservacion.text.toString()
            var encuesta: EncuestaDto = EncuestaDto(null,null,observacion,false,ordenId,valoracion)
            viewModel.createEncuesta(encuesta)
            Log.i("-/Encuesta/->", encuesta.toString())
            startActivity(Intent(requireContext(), ClienteActivity::class.java))
        }

    }

    private fun iniciarUI(){
        binding.lyEncuestaForm.visibility = View.GONE
        binding.lyEncuestaMensaje.visibility = View.VISIBLE
        binding.tvEncuestaMensaje.setText("Su nueva orden de servicio debe estar marcada como RESUELTA para enviar una encuesta de atención")
    }

    private fun seleccionarValoracion(): String {
        var valoracion:String = "CONFORME"
        if(binding.rbEncuestaMala.isChecked) {
            valoracion = "MALA"
        }
        if(binding.rbEncuestaConforme.isChecked) {
            valoracion = "CONFORME"
        }
        if(binding.rbEncuestaExcelente.isChecked) {
            valoracion = "EXCELENTE"
        }
        return valoracion
    }

    override fun getViewModel(): Class<EncuestaViewModel> = EncuestaViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEncuestaBinding = FragmentEncuestaBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): EncuestaRepository {
        var token = runBlocking { userPreferences.authToken.first() }
        var api = remoteDataSource.buildApi(EncuestaApi::class.java, token)
        return EncuestaRepository(api)
    }

}