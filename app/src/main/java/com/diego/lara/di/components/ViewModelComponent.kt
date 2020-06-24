package com.diego.lara.di.components

import com.diego.lara.activities.MainActivity
import com.diego.lara.di.modules.ViewModelModule
import dagger.Component

@Component(modules = [ViewModelModule::class])
interface ViewModelComponent {

    fun inject(view: MainActivity)

}