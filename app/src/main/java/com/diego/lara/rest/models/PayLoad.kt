package com.diego.lara.rest.models

data class PayLoad(
    val Usuario: Usuario,
    val Proyecto: Proyecto
)

data class Usuario(
    val Id: String
)

data class Proyecto(
    val Id: String,
    val Ufechadescarga: Int
)