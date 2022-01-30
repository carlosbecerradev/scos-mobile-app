package com.example.scos_mobile_app.data.repository

import com.example.scos_mobile_app.data.UserPreferences
import com.example.scos_mobile_app.data.network.AuthApi
import com.example.scos_mobile_app.data.responses.LoginRequest

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository() {

    suspend fun login(
        username: String,
        password: String
    ) = safeApiCall {
        api.login(LoginRequest(username, password))
    }

    suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
    }

}