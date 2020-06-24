package com.diego.lara.di.components

import com.diego.lara.domain.TiendasRepository
import com.diego.lara.di.modules.DataSourceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataSourceModule::class])
interface DataSourceComponent {

    fun inject(mainRepository: TiendasRepository)

}