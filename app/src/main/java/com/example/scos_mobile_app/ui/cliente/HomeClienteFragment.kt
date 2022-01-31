package com.example.scos_mobile_app.ui.cliente

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.scos_mobile_app.data.network.ClienteApi
import com.example.scos_mobile_app.data.network.Resource
import com.example.scos_mobile_app.data.repository.ClienteRepository
import com.example.scos_mobile_app.data.responses.Cliente
import com.example.scos_mobile_app.data.responses.OrdenDeServicioDto
import com.example.scos_mobile_app.databinding.FragmentHomeClienteBinding
import com.example.scos_mobile_app.ui.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeClienteFragment : BaseFragment<HomeClienteViewModel, FragmentHomeClienteBinding, ClienteRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniciarOrdenUI()
        val username = runBlocking { userPreferences.username.first() }
        viewModel.getCliente(username.toString())

        viewModel.cliente.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    var cliente: Cliente = it.value
                    updateUI(cliente)
                    Log.e("--//-->", it.value.toString())
                    lifecycleScope.launch {
                        userPreferences.saveClienteId(cliente.id)
                        userPreferences.saveTipoDeServicioNombre(cliente.tipoDeServicio)
                        viewModel.getUltimaOrden(cliente.id)
                    }
                }
            }
        })

        viewModel.ordenDeServicioDto.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    var orden: OrdenDeServicioDto = it.value
                    if( orden.estado.equals("CERRADA")) {
                        binding.tvOrdenMensaje.setText("Aún no ha generado una Orden de Servicio")
                    }
                    if( orden.estado.equals("CREADA")) {
                        binding.tvOrdenMensaje.setText("Orden de servicio actual")
                        binding.lyOrdenBlock.visibility = View.VISIBLE
                        binding.lyOrdenCodigo.visibility = View.VISIBLE
                        binding.tvOrdenCodigo.setText(orden.id.toString())
                        binding.lyOrdenFechaCreacion.visibility = View.VISIBLE
                        binding.tvOrdenFechaCreacion.setText(orden.fechaDeCreacion?.replace("T", "  "))
                        binding.lyOrdenTipoDeIncidencia.visibility = View.VISIBLE
                        binding.tvOrdenTipoDeIncidencia.setText(orden.tipoDeIncidencia)
                        binding.lyOrdenDescripcion.visibility = View.VISIBLE
                        binding.tvOrdenDescripciion.setText(orden.descripcionDelProblema)
                        binding.lyOrdenEstado.visibility = View.VISIBLE
                        binding.tvOrdenEstado.setText(orden.estado)
                        binding.lyOrdenImagen.visibility = View.VISIBLE
                    }
                    if(orden.estado.equals("ASIGNADA")) {
                        binding.tvOrdenMensaje.setText("Orden de servicio actual")
                        binding.lyOrdenBlock.visibility = View.VISIBLE
                        binding.lyOrdenCodigo.visibility = View.VISIBLE
                        binding.tvOrdenCodigo.setText(orden.id.toString())
                        binding.lyOrdenFechaCreacion.visibility = View.VISIBLE
                        binding.tvOrdenFechaCreacion.setText(orden.fechaDeCreacion?.replace("T", "  "))
                        binding.lyOrdenTipoDeIncidencia.visibility = View.VISIBLE
                        binding.tvOrdenTipoDeIncidencia.setText(orden.tipoDeIncidencia)
                        binding.lyOrdenDescripcion.visibility = View.VISIBLE
                        binding.tvOrdenDescripciion.setText(orden.descripcionDelProblema)
                        binding.lyOrdenEstado.visibility = View.VISIBLE
                        binding.tvOrdenEstado.setText(orden.estado)
                        binding.lyOrdenImagen.visibility = View.VISIBLE
                        binding.lyOrdenFechaAsignacion.visibility = View.VISIBLE
                        binding.tvOrdenFechaAsignacion.setText(orden.fechaDeAsignacion?.replace("T", "  "))
                        binding.lyOrdenTecnico.visibility = View.VISIBLE
                        binding.lyOrdenUbicacion.visibility = View.VISIBLE
                    }
                    if(orden.estado.equals("VISITADA")) {
                        binding.tvOrdenMensaje.setText("Orden de servicio actual")
                        binding.lyOrdenBlock.visibility = View.VISIBLE
                        binding.lyOrdenCodigo.visibility = View.VISIBLE
                        binding.tvOrdenCodigo.setText(orden.id.toString())
                        binding.lyOrdenFechaCreacion.visibility = View.VISIBLE
                        binding.tvOrdenFechaCreacion.setText(orden.fechaDeCreacion?.replace("T", "  "))
                        binding.lyOrdenTipoDeIncidencia.visibility = View.VISIBLE
                        binding.tvOrdenTipoDeIncidencia.setText(orden.tipoDeIncidencia)
                        binding.lyOrdenDescripcion.visibility = View.VISIBLE
                        binding.tvOrdenDescripciion.setText(orden.descripcionDelProblema)
                        binding.lyOrdenEstado.visibility = View.VISIBLE
                        binding.tvOrdenEstado.setText(orden.estado)
                        binding.lyOrdenImagen.visibility = View.VISIBLE
                        binding.lyOrdenFechaAsignacion.visibility = View.VISIBLE
                        binding.tvOrdenFechaAsignacion.setText(orden.fechaDeAsignacion?.replace("T", "  "))
                        binding.lyOrdenTecnico.visibility = View.VISIBLE
                        binding.lyOrdenFechaLlegada.visibility = View.VISIBLE
                        binding.tvOrdenFechaLlegada.setText(orden.fechaDeLlegada?.replace("T", "  "))
                        binding.lyOrdenConstanciaVisita.visibility = View.VISIBLE
                    }
                    if(orden.estado.equals("CANCELADA")) {
                        binding.tvOrdenMensaje.setText("Orden de servicio cancelada")
                        binding.lyOrdenBlock.visibility = View.VISIBLE
                        binding.lyOrdenCodigo.visibility = View.VISIBLE
                        binding.tvOrdenCodigo.setText(orden.id.toString())
                        binding.lyOrdenFechaCreacion.visibility = View.VISIBLE
                        binding.tvOrdenFechaCreacion.setText(orden.fechaDeCreacion?.replace("T", "  "))
                        binding.lyOrdenTipoDeIncidencia.visibility = View.VISIBLE
                        binding.tvOrdenTipoDeIncidencia.setText(orden.tipoDeIncidencia)
                        binding.lyOrdenDescripcion.visibility = View.VISIBLE
                        binding.tvOrdenDescripciion.setText(orden.descripcionDelProblema)
                        binding.lyOrdenEstado.visibility = View.VISIBLE
                        binding.tvOrdenEstado.setText(orden.estado)
                        binding.lyOrdenImagen.visibility = View.VISIBLE
                        binding.lyOrdenFechaAsignacion.visibility = View.VISIBLE
                        binding.tvOrdenFechaAsignacion.setText(orden.fechaDeAsignacion?.replace("T", "  "))
                        binding.lyOrdenTecnico.visibility = View.VISIBLE
                        binding.lyOrdenFechaLlegada.visibility = View.VISIBLE
                        binding.tvOrdenFechaLlegada.setText(orden.fechaDeLlegada?.replace("T", "  "))
                        binding.lyOrdenConstanciaVisita.visibility = View.VISIBLE
                        binding.lyOrdenFechaCancelacion.visibility = View.VISIBLE
                        binding.tvOrdenFechaCancelacion.setText(orden.fechaDeCancelacion?.replace("T", "  "))
                        binding.lyOrdenMotivoCancelacion.visibility = View.VISIBLE
                    }
                    if(orden.estado.equals("RESUELTA")) {
                        binding.tvOrdenMensaje.setText("Orden de servicio resuelta, realice la encuesta de atención")
                        binding.lyOrdenBlock.visibility = View.VISIBLE
                        binding.lyOrdenCodigo.visibility = View.VISIBLE
                        binding.tvOrdenCodigo.setText(orden.id.toString())
                        binding.lyOrdenFechaCreacion.visibility = View.VISIBLE
                        binding.tvOrdenFechaCreacion.setText(orden.fechaDeCreacion?.replace("T", "  "))
                        binding.lyOrdenTipoDeIncidencia.visibility = View.VISIBLE
                        binding.tvOrdenTipoDeIncidencia.setText(orden.tipoDeIncidencia)
                        binding.lyOrdenDescripcion.visibility = View.VISIBLE
                        binding.tvOrdenDescripciion.setText(orden.descripcionDelProblema)
                        binding.lyOrdenEstado.visibility = View.VISIBLE
                        binding.tvOrdenEstado.setText(orden.estado)
                        binding.lyOrdenFechaAsignacion.visibility = View.VISIBLE
                        binding.tvOrdenFechaAsignacion.setText(orden.fechaDeAsignacion?.replace("T", "  "))
                        binding.lyOrdenFechaLlegada.visibility = View.VISIBLE
                        binding.tvOrdenFechaLlegada.setText(orden.fechaDeLlegada?.replace("T", "  "))
                        binding.lyOrdenFechaResolucion.visibility = View.VISIBLE
                        binding.tvOrdenFechaResolucion.setText(orden.fechaDeResolucion?.replace("T", "  "))
                        binding.lyOrdenImagen.visibility = View.VISIBLE
                        binding.lyOrdenTecnico.visibility = View.VISIBLE
                        binding.lyOrdenConstanciaVisita.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun updateUI(cliente: Cliente) {
        var mensaje = "Bienvenido " + cliente.nombres + " " + cliente.apellidos
        binding.txtWelcomeCliente.setText(mensaje)
    }

    private fun iniciarOrdenUI() {
        binding.lyOrdenBlock.visibility = View.GONE
        binding.lyOrdenCodigo.visibility = View.GONE
        binding.lyOrdenFechaCreacion.visibility = View.GONE
        binding.lyOrdenTipoDeIncidencia.visibility = View.GONE
        binding.lyOrdenDescripcion.visibility = View.GONE
        binding.lyOrdenEstado.visibility = View.GONE
        binding.lyOrdenFechaAsignacion.visibility = View.GONE
        binding.lyOrdenFechaLlegada.visibility = View.GONE
        binding.lyOrdenFechaCancelacion.visibility = View.GONE
        binding.lyOrdenFechaResolucion.visibility = View.GONE
        binding.lyOrdenImagen.visibility = View.GONE
        binding.lyOrdenTecnico.visibility = View.GONE
        binding.lyOrdenConstanciaVisita.visibility = View.GONE
        binding.lyOrdenMotivoCancelacion.visibility = View.GONE
        binding.lyOrdenUbicacion.visibility = View.GONE
    }

    override fun getViewModel(): Class<HomeClienteViewModel> = HomeClienteViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeClienteBinding = FragmentHomeClienteBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): ClienteRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(ClienteApi::class.java, token)
        return ClienteRepository(api)
    }

}