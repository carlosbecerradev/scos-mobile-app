package com.example.scos_mobile_app.data.responses

data class TipoDeIncidencia(
    val tipoDeIncidenciaId: Int,
    val nombre: String,
    val activo: Boolean,
    val tipoDeServicio: TipoDeServicio,
    val fechaDeCreacion: String,
)