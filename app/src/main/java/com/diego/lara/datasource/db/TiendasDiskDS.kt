package com.diego.lara.datasource.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.diego.lara.models.Tiendas

@Dao
interface TiendasDiskDS {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Tiendas>)

    @Query("DELETE FROM tiendas")
    fun truncate()

    @Query("SELECT * FROM tiendas WHERE sucursal like :name or determinante like :name or cadena like :name ORDER BY determinante")
    fun readTiendas(name: String): LiveData<List<Tiendas>>
}