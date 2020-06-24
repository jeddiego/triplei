package com.diego.lara

import android.app.Application
import android.content.Context
import com.diego.lara.di.components.DaggerUtilComponent
import com.diego.lara.providers.AppExecutors
import com.diego.lara.providers.AppFeaturesProvider
import com.diego.lara.providers.ResourceProvider
import com.diego.lara.di.components.UtilComponent
import com.diego.lara.di.modules.ContextModule

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        utilComponent = DaggerUtilComponent.builder()
            .contextModule(ContextModule(applicationContext))
            .build()
    }

    companion object {
        lateinit var utilComponent: UtilComponent

        val appContext: Context
            get() = utilComponent.appContext

        val appResourceProvider: ResourceProvider
            get() = utilComponent.resourceProvider

        val appFeaturesProvider: AppFeaturesProvider
            get() = utilComponent.appFeaturesProvider

        val appExecutors: AppExecutors
            get() = utilComponent.appExecutors
    }
}