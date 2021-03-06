package com.example.scos_mobile_app.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.scos_mobile_app.data.repository.*
import com.example.scos_mobile_app.ui.auth.AuthViewModel
import com.example.scos_mobile_app.ui.cliente.EncuestaViewModel
import com.example.scos_mobile_app.ui.cliente.HomeClienteViewModel
import com.example.scos_mobile_app.ui.cliente.NuevaOrdenViewModel
import com.example.scos_mobile_app.ui.tecnico.HomeTecnicoViewModel
import com.example.scos_mobile_app.ui.tecnico.OrdenesViewModel
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
            modelClass.isAssignableFrom(EncuestaViewModel::class.java) -> EncuestaViewModel(repository as EncuestaRepository) as T
            modelClass.isAssignableFrom(HomeTecnicoViewModel::class.java) -> HomeTecnicoViewModel(repository as HomeTecnicoRepository) as T
            modelClass.isAssignableFrom(OrdenesViewModel::class.java) -> OrdenesViewModel(repository as OrdenesRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}