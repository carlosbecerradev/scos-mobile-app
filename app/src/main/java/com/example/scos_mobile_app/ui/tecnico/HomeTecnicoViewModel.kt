package com.example.scos_mobile_app.ui.tecnico

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scos_mobile_app.data.network.Resource
import com.example.scos_mobile_app.data.repository.HomeTecnicoRepository
import com.example.scos_mobile_app.data.responses.Empleado
import com.example.scos_mobile_app.data.responses.OrdenDeServicioDto
import kotlinx.coroutines.launch

class HomeTecnicoViewModel(
    private val repository: HomeTecnicoRepository
): ViewModel() {

    private val _tecnico: MutableLiveData<Resource<Empleado>> = MutableLiveData()
    val tecnico: LiveData<Resource<Empleado>>
        get() = _tecnico

    fun getTecnico(username: String) = viewModelScope.launch {
        _tecnico.value = repository.getTecnico(username)
    }

    private val _ordenDeServicioDto: MutableLiveData<Resource<OrdenDeServicioDto>> = MutableLiveData()
    val ordenDeServicioDto: LiveData<Resource<OrdenDeServicioDto>>
        get() = _ordenDeServicioDto

    fun getUltimaOrden(tecnicoId: Long) = viewModelScope.launch {
        _ordenDeServicioDto.value = repository.getUltimaOrden(tecnicoId)
    }

}