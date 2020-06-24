package com.diego.lara.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.diego.lara.models.Tiendas

@Database(entities = [Tiendas::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val tiendasDiskDS: TiendasDiskDS

}