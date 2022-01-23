package com.example.scos_mobile_app.responses

data class LoginResponse (
    val nombreDeUsuario: String,
    val rol: String,
    val sede: String,
    val tokenDeAcceso: String
)