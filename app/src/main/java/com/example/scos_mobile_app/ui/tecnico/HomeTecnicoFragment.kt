package com.example.scos_mobile_app.ui.tecnico

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.scos_mobile_app.data.network.ClienteApi
import com.example.scos_mobile_app.data.network.HomeTecnicoApi
import com.example.scos_mobile_app.data.network.Resource
import com.example.scos_mobile_app.data.repository.ClienteRepository
import com.example.scos_mobile_app.data.repository.HomeTecnicoRepository
import com.example.scos_mobile_app.data.responses.Cliente
import com.example.scos_mobile_app.data.responses.Empleado
import com.example.scos_mobile_app.data.responses.OrdenDeServicioDto
import com.example.scos_mobile_app.databinding.FragmentHomeTecnicoBinding
import com.example.scos_mobile_app.ui.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeTecnicoFragment : BaseFragment<HomeTecnicoViewModel, FragmentHomeTecnicoBinding, HomeTecnicoRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniciarOrdenUI()
        val username = runBlocking { userPreferences.username.first() }
        if (username != null) {
            viewModel.getTecnico(username.toString())
        }

        viewModel.tecnico.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    var tecnico = it.value
                    Log.e("--//-->", tecnico.toString())
                    updateUI(tecnico)
                    lifecycleScope.launch {
                        userPreferences.saveTecnicoId(tecnico.id.toLong())
                        viewModel.getUltimaOrden(tecnico.id.toLong())
                    }
                }
            }
        })

        viewModel.ordenDeServicioDto.observe(viewLifecycleOwner, {
            when(it) {
                is Resource.Success -> {
                    var orden: OrdenDeServicioDto = it.value
                    if( orden.estado.equals("CERRADA")) {
                        binding.tvOrdenMensaje.setText("Aún no ha aceptado atender una Orden de Servicio")
                        iniciarOrdenUI()
                    }
                    if(orden.estado.equals("ASIGNADA")) {
                        binding.tvOrdenMensaje.setText("Orden de servicio en atención")
                        binding.btnFormularioCancelar.visibility = View.VISIBLE
                        binding.btnFormularioConstancia.visibility = View.VISIBLE
                        binding.btnResolverOrden.visibility = View.GONE
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
                        binding.lyOrdenCliente.visibility = View.VISIBLE
                        binding.lyOrdenUbicacion.visibility = View.VISIBLE
                    }
                    if(orden.estado.equals("VISITADA")) {
                        binding.tvOrdenMensaje.setText("Orden de servicio  en atención")
                        binding.btnFormularioCancelar.visibility = View.VISIBLE
                        binding.btnFormularioConstancia.visibility = View.GONE
                        binding.btnResolverOrden.visibility = View.VISIBLE
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
                        binding.lyOrdenCliente.visibility = View.VISIBLE
                        binding.lyOrdenFechaLlegada.visibility = View.VISIBLE
                        binding.tvOrdenFechaLlegada.setText(orden.fechaDeLlegada?.replace("T", "  "))
                        binding.lyOrdenConstanciaVisita.visibility = View.VISIBLE
                    }
                    if(orden.estado.equals("CANCELADA")) {
                        binding.tvOrdenMensaje.setText("Orden de servicio cancelada")
                        binding.btnFormularioCancelar.visibility = View.GONE
                        binding.btnFormularioConstancia.visibility = View.GONE
                        binding.btnResolverOrden.visibility = View.GONE
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
                        binding.lyOrdenCliente.visibility = View.VISIBLE
                        binding.lyOrdenFechaLlegada.visibility = View.VISIBLE
                        binding.tvOrdenFechaLlegada.setText(orden.fechaDeLlegada?.replace("T", "  "))
                        binding.lyOrdenConstanciaVisita.visibility = View.VISIBLE
                        binding.lyOrdenFechaCancelacion.visibility = View.VISIBLE
                        binding.tvOrdenFechaCancelacion.setText(orden.fechaDeCancelacion?.replace("T", "  "))
                        binding.lyOrdenMotivoCancelacion.visibility = View.VISIBLE
                    }
                    if(orden.estado.equals("RESUELTA")) {
                        binding.tvOrdenMensaje.setText("Orden de servicio resuelta, realice la encuesta de atención")
                        binding.btnFormularioCancelar.visibility = View.GONE
                        binding.btnFormularioConstancia.visibility = View.GONE
                        binding.btnResolverOrden.visibility = View.GONE
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
                        binding.lyOrdenCliente.visibility = View.VISIBLE
                        binding.lyOrdenConstanciaVisita.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
    private fun updateUI(tecnico: Empleado) {
        var mensaje = "Bienvenido " + tecnico.nombres + " " + tecnico.apellidos
        binding.txtWelcomeTecnico.setText(mensaje)
    }

    private fun iniciarOrdenUI() {
        binding.btnFormularioCancelar.visibility = View.GONE
        binding.btnFormularioConstancia.visibility = View.GONE
        binding.btnResolverOrden.visibility = View.GONE
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
        binding.lyOrdenCliente.visibility = View.GONE
        binding.lyOrdenConstanciaVisita.visibility = View.GONE
        binding.lyOrdenMotivoCancelacion.visibility = View.GONE
        binding.lyOrdenUbicacion.visibility = View.GONE
    }

    override fun getViewModel(): Class<HomeTecnicoViewModel> = HomeTecnicoViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeTecnicoBinding = FragmentHomeTecnicoBinding.inflate(inflater , container, false)

    override fun getFragmentRepository(): HomeTecnicoRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(HomeTecnicoApi::class.java, token)
        return HomeTecnicoRepository(api)
    }


}