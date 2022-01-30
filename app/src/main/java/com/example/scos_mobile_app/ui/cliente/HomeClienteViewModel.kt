package com.example.scos_mobile_app.ui.cliente

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scos_mobile_app.data.network.Resource
import com.example.scos_mobile_app.data.repository.ClienteRepository
import com.example.scos_mobile_app.data.responses.Cliente
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
}