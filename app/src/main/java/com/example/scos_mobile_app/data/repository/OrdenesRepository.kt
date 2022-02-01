package com.example.scos_mobile_app.data.repository

import com.example.scos_mobile_app.data.network.OrdenesApi

class OrdenesRepository(
    private val api: OrdenesApi
): BaseRepository() {

    suspend fun getOrdenesBySede(sede: String) = safeApiCall {
        api.getOrdenesBySede(sede)
    }
}