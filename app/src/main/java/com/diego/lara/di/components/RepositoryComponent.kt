package com.diego.lara.di.components

import com.diego.lara.di.modules.RepositoryModule
import com.diego.lara.viewModel.TiendasViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface RepositoryComponent {

    fun inject(viewModel: TiendasViewModel?)
}