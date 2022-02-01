package com.example.scos_mobile_app.data.network

import com.example.scos_mobile_app.data.responses.EncuestaDto
import com.example.scos_mobile_app.data.responses.OrdenDeServicioDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EncuestaApi {

    @GET("v1/orden-de-servicio/ultima/cliente/id/{clienteId}")
    suspend fun getUltimaOrden(@Path("clienteId") clienteId: Long): OrdenDeServicioDto

    @POST("v1/encuesta-de-atencion")
    suspend fun createEncuesta(
        @Body encuesta: EncuestaDto
    )

}