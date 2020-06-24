package com.diego.lara.di.modules

import android.content.Context
import com.diego.lara.providers.AppExecutors
import com.diego.lara.providers.AppFeaturesProvider
import com.diego.lara.providers.ResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class UtilModule {
    @Provides
    @Singleton
    fun provideAppExecutors(): AppExecutors {
        return AppExecutors()
    }

    @Provides
    @Singleton
    fun providerResourceProvider(context: Context): ResourceProvider {
        return ResourceProvider(context)
    }

    @Provides
    @Singleton
    fun provideAppFeaturesProvider(context: Context): AppFeaturesProvider {
        return AppFeaturesProvider(context)
    }
}