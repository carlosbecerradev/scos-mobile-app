package com.example.scos_mobile_app.data.repository

import com.example.scos_mobile_app.data.network.HomeTecnicoApi

class HomeTecnicoRepository(
    private val api: HomeTecnicoApi
): BaseRepository() {

    suspend fun getTecnico(username: String) = safeApiCall {
        api.getTecnico(username)
    }

    suspend fun getUltimaOrden(tecnicoId: Long) = safeApiCall {
        api.getUltimaOrden(tecnicoId)
    }
}