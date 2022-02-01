package com.example.scos_mobile_app.ui.tecnico

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scos_mobile_app.data.network.Resource
import com.example.scos_mobile_app.data.repository.OrdenesRepository
import com.example.scos_mobile_app.data.responses.OrdenDeServicioDto
import kotlinx.coroutines.launch

class OrdenesViewModel(
    private val repository: OrdenesRepository
): ViewModel() {

    private val _ordenes: MutableLiveData<Resource<List<OrdenDeServicioDto>>> = MutableLiveData()
    val ordenes: LiveData<Resource<List<OrdenDeServicioDto>>>
        get() = _ordenes

    fun getOrdenesBySede(sede: String) = viewModelScope.launch {
        _ordenes.value = repository.getOrdenesBySede(sede)
    }

}