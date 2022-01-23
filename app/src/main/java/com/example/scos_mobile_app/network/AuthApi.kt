package com.example.scos_mobile_app.network

import com.example.scos_mobile_app.responses.LoginRequest
import com.example.scos_mobile_app.responses.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {

    @Headers("Content-Type: application/json")
    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse
}