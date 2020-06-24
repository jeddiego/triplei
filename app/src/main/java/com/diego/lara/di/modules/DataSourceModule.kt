package com.diego.lara.di.modules

import com.diego.lara.datasource.db.AppDatabase
import com.diego.lara.datasource.db.TiendasDiskDS
import com.diego.lara.datasource.web.TiendasWebDS
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class])
class DataSourceModule {

    @Singleton
    @Provides
    fun provideMoviesWebDS(): TiendasWebDS {
        return TiendasWebDS()
    }

    @Singleton
    @Provides
    fun provideMoviesDiskDS(database: AppDatabase): TiendasDiskDS {
        return database.tiendasDiskDS
    }

}