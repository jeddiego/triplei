package com.diego.lara.rest.models

import com.diego.lara.models.Tiendas
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OnTiendasResponse(
    @SerializedName("getConjuntotiendasUsuarioResult")
    @Expose
    val tiendas: List<Tiendas>
)