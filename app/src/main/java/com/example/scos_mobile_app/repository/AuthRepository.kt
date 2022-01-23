package com.example.scos_mobile_app.repository

import com.example.scos_mobile_app.network.AuthApi
import com.example.scos_mobile_app.responses.LoginRequest

class AuthRepository(
    private val api: AuthApi
) : BaseRepository() {

    suspend fun login(
        username: String,
        password: String
    ) = safeApiCall {
        api.login(LoginRequest(username, password))
    }
}