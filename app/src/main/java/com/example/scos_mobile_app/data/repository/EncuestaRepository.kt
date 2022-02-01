package com.example.scos_mobile_app.data.repository

import com.example.scos_mobile_app.data.network.EncuestaApi
import com.example.scos_mobile_app.data.responses.EncuestaDto

class EncuestaRepository(
    private val api: EncuestaApi
): BaseRepository() {

    suspend fun getUltimaOrden(clienteId: Long) = safeApiCall {
        api.getUltimaOrden(clienteId)
    }

    suspend fun createEncuesta(encuesta: EncuestaDto) = safeApiCall {
        api.createEncuesta(encuesta)
    }

}