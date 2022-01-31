package com.example.scos_mobile_app.data.network

import com.example.scos_mobile_app.data.responses.Cliente
import com.example.scos_mobile_app.data.responses.OrdenDeServicioDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ClienteApi {

    @GET("v1/cliente/nombre-de-usuario/{username}")
    suspend fun getCliente(@Path("username") username:String): Cliente

    @GET("v1/orden-de-servicio/ultima/cliente/id/{clienteId}")
    suspend fun getUltimOrden(@Path("clienteId") clienteId: Long): OrdenDeServicioDto

}