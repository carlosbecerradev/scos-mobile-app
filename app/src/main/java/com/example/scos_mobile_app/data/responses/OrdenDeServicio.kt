package com.example.scos_mobile_app.data.responses

data class OrdenDeServicio(
    val cliente: ClienteX?,
    val descripcionDelProblema: String?,
    val empleado: EmpleadoX?,
    val estado: Any?,
    val fechaDeAsignacion: Any?,
    val fechaDeCancelacion: Any?,
    val fechaDeCierre: Any?,
    val fechaDeCreacion: Any?,
    val fechaDeLlegada: Any?,
    val fechaDeResolucion: Any?,
    val imagenUrl: String?,
    val motivoDeCancelacion: Any?,
    val ordenDeServicioId: Any?,
    val revisada: Boolean,
    val tipoDeIncidencia: TipoDeIncidenciaX?
)