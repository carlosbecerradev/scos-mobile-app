package com.example.scos_mobile_app.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.scos_mobile_app.data.repository.AuthRepository
import com.example.scos_mobile_app.data.repository.BaseRepository
import com.example.scos_mobile_app.data.repository.ClienteRepository
import com.example.scos_mobile_app.data.repository.NuevaOrdenRepository
import com.example.scos_mobile_app.ui.auth.AuthViewModel
import com.example.scos_mobile_app.ui.cliente.HomeClienteViewModel
import com.example.scos_mobile_app.ui.cliente.NuevaOrdenViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory (
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeClienteViewModel::class.java) -> HomeClienteViewModel(repository as ClienteRepository) as T
            modelClass.isAssignableFrom(NuevaOrdenViewModel::class.java) -> NuevaOrdenViewModel(repository as NuevaOrdenRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}