package com.example.scos_mobile_app.data.responses

data class TipoDeServicio(
    val tipoDeServicioId: Int,
    val nombre: String,
    val activo: Boolean,
    val fechaDeCreacion: String,
)