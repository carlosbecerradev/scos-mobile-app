package com.example.scos_mobile_app.ui.cliente

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scos_mobile_app.data.network.Resource
import com.example.scos_mobile_app.data.repository.EncuestaRepository
import com.example.scos_mobile_app.data.responses.EncuestaDto
import com.example.scos_mobile_app.data.responses.OrdenDeServicioDto
import kotlinx.coroutines.launch

class EncuestaViewModel(
    private val repository: EncuestaRepository
): ViewModel() {

    private val _ordenDeServicioDto: MutableLiveData<Resource<OrdenDeServicioDto>> = MutableLiveData()
    val ordenDeServicioDto: LiveData<Resource<OrdenDeServicioDto>>
        get() = _ordenDeServicioDto

    fun getUltimaOrden(clienteId: Long) = viewModelScope.launch {
        _ordenDeServicioDto.value = repository.getUltimaOrden(clienteId)
    }

    fun createEncuesta(encuesta: EncuestaDto) = viewModelScope.launch {
        repository.createEncuesta(encuesta)
    }

}