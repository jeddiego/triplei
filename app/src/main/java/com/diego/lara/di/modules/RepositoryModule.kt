package com.diego.lara.di.modules

import com.diego.lara.domain.TiendasRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideTiendasRepository(): TiendasRepository {
        return TiendasRepository()
    }
}