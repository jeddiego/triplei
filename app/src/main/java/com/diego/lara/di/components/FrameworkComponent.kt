package com.diego.lara.di.components

import com.diego.lara.datasource.web.TiendasWebDS
import com.diego.lara.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface FrameworkComponent {

    fun inject(moviesWebDS: TiendasWebDS)

}