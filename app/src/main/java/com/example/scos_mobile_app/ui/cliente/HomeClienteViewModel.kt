package com.example.scos_mobile_app.ui.cliente

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scos_mobile_app.data.network.Resource
import com.example.scos_mobile_app.data.repository.ClienteRepository
import com.example.scos_mobile_app.data.responses.Cliente
import com.example.scos_mobile_app.data.responses.OrdenDeServicioDto
import kotlinx.coroutines.launch

class HomeClienteViewModel(
    private val repository: ClienteRepository
): ViewModel() {

    private val _cliente: MutableLiveData<Resource<Cliente>> = MutableLiveData()
    val cliente: LiveData<Resource<Cliente>>
        get() = _cliente

    fun getCliente(username: String) = viewModelScope.launch {
        _cliente.value = repository.getCliente(username)
    }

    private val _ordenDeServicioDto: MutableLiveData<Resource<OrdenDeServicioDto>> = MutableLiveData()
    val ordenDeServicioDto: LiveData<Resource<OrdenDeServicioDto>>
        get() = _ordenDeServicioDto

    fun getUltimaOrden(clienteId: Long) = viewModelScope.launch {
        _ordenDeServicioDto.value = repository.getUltimOrden(clienteId)
    }

}