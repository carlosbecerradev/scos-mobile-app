package com.example.scos_mobile_app.data.repository

import com.example.scos_mobile_app.data.network.ClienteApi

class ClienteRepository(
    private val api:ClienteApi
): BaseRepository() {

    suspend fun getCliente(username: String) = safeApiCall {
        api.getCliente(username)
    }

    suspend fun getUltimOrden(clienteId: Long) = safeApiCall {
        api.getUltimOrden(clienteId)
    }

}