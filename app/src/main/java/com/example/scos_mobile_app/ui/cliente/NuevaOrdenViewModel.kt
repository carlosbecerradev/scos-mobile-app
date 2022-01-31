package com.example.scos_mobile_app.ui.cliente

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scos_mobile_app.data.network.Resource
import com.example.scos_mobile_app.data.repository.NuevaOrdenRepository
import com.example.scos_mobile_app.data.responses.OrdenDeServicio
import com.example.scos_mobile_app.data.responses.OrdenDeServicioDto
import com.example.scos_mobile_app.data.responses.TipoDeIncidencia
import kotlinx.coroutines.launch

class NuevaOrdenViewModel(
    private val repository: NuevaOrdenRepository
): ViewModel() {

    private val _tiposDeIncidencia: MutableLiveData<Resource<List<TipoDeIncidencia>>> = MutableLiveData()
    val tiposDeIncidencia: LiveData<Resource<List<TipoDeIncidencia>>>
        get() = _tiposDeIncidencia

    fun getTiposdDeIncidencia(nombre: String) = viewModelScope.launch {
        _tiposDeIncidencia.value = repository.getTiposDeIncidencia(nombre)
    }

    fun createOrdenDeServicio(ordenDeServicio: OrdenDeServicio) = viewModelScope.launch {
        repository.createOrden(ordenDeServicio)
    }

    private val _ordenDeServicioDto: MutableLiveData<Resource<OrdenDeServicioDto>> = MutableLiveData()
    val ordenDeServicioDto: LiveData<Resource<OrdenDeServicioDto>>
        get() = _ordenDeServicioDto

    fun getUltimaOrden(clienteId: Long) = viewModelScope.launch {
        _ordenDeServicioDto.value = repository.getUltimaOrden(clienteId)
    }

}