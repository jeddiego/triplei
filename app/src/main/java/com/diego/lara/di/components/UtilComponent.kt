package com.diego.lara.di.components

import android.content.Context
import com.diego.lara.providers.AppExecutors
import com.diego.lara.providers.AppFeaturesProvider
import com.diego.lara.providers.ResourceProvider
import com.diego.lara.di.modules.UtilModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UtilModule::class])
interface UtilComponent {

    val appContext: Context

    val appExecutors: AppExecutors

    val resourceProvider: ResourceProvider

    val appFeaturesProvider: AppFeaturesProvider
}