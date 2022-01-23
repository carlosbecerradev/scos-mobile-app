package com.example.scos_mobile_app.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scos_mobile_app.network.Resource
import com.example.scos_mobile_app.repository.AuthRepository
import com.example.scos_mobile_app.responses.LoginRequest
import com.example.scos_mobile_app.responses.LoginResponse
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get()=_loginResponse

    fun login(
        username: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = repository.login(username, password)
    }
}