package com.example.scos_mobile_app.data.network

import com.example.scos_mobile_app.data.responses.Empleado
import com.example.scos_mobile_app.data.responses.OrdenDeServicioDto
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeTecnicoApi {

    @GET("v1/empleado/nombre-de-usuario/{username}")
    suspend fun getTecnico(@Path("username") username:String): Empleado

    @GET("v1/orden-de-servicio/ultima/tecnico/id/{tecnicoId}")
    suspend fun getUltimaOrden(@Path("tecnicoId") tecnicoId: Long): OrdenDeServicioDto
}