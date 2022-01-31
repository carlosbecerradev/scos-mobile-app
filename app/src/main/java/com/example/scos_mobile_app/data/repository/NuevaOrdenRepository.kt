package com.example.scos_mobile_app.data.repository

import com.example.scos_mobile_app.data.network.NuevaOrdenApi
import com.example.scos_mobile_app.data.responses.OrdenDeServicio

class NuevaOrdenRepository(
    private val api: NuevaOrdenApi
): BaseRepository() {

    suspend fun createOrden(ordenDeServicio: OrdenDeServicio) = safeApiCall {
        api.createOrden(ordenDeServicio)
    }

    suspend fun getTiposDeIncidencia(nombre: String) = safeApiCall {
        api.getTiposDeIncidencia(nombre)
    }

}