package com.example.scos_mobile_app.data.network

import com.example.scos_mobile_app.data.responses.OrdenDeServicioDto
import retrofit2.http.GET
import retrofit2.http.Path

interface OrdenesApi {

    @GET("v1/orden-de-servicio/creada/sede/{sede}")
    suspend fun getOrdenesBySede(
        @Path("sede") sede:String
    ): List<OrdenDeServicioDto>
}