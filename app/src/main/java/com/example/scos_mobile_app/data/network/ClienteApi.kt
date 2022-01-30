package com.example.scos_mobile_app.data.network

import com.example.scos_mobile_app.data.responses.Cliente
import retrofit2.http.GET
import retrofit2.http.Path

interface ClienteApi {

    @GET("v1/cliente/nombre-de-usuario/{username}")
    suspend fun getCliente(@Path("username") username:String): Cliente
}