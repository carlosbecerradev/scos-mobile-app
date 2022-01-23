package com.example.scos_mobile_app.data.network

import com.example.scos_mobile_app.data.responses.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): String
}