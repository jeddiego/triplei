package com.diego.lara.di.modules

import android.content.Context
import androidx.room.Room
import com.diego.lara.datasource.db.AppDatabase
import com.diego.lara.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Constants.Db.DB_NAME
        )
            .build()
    }
}