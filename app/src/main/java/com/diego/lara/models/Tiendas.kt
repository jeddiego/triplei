package com.diego.lara.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tiendas")
data class Tiendas(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Int,
    @SerializedName("determinante")
    @ColumnInfo(name = "determinante")
    val determinante: String,
    @SerializedName("Cadena")
    @ColumnInfo(name = "cadena")
    val cadena: String,
    @SerializedName("Sucursal")
    @ColumnInfo(name = "sucursal")
    val sucursal: String,
    @SerializedName("Latitud")
    @ColumnInfo(name = "latitud")
    val latitud: Double,
    @SerializedName("Longitud")
    @ColumnInfo(name = "longitud")
    val longitud: Double
)
