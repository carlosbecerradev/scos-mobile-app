package com.example.scos_mobile_app.data.network

import com.example.scos_mobile_app.data.responses.OrdenDeServicio
import com.example.scos_mobile_app.data.responses.TipoDeIncidencia
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.ArrayList

interface NuevaOrdenApi {

    @POST("v1/orden-de-servicio")
    suspend fun createOrden(
        @Body orden: OrdenDeServicio
    )

    @GET("v1/tipo-de-incidencia/tipo-de-servicio/nombre/{nombre}")
    suspend fun getTiposDeIncidencia(
        @Path("nombre") nombre:String
    ): ArrayList<TipoDeIncidencia>

}