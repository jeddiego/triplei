package com.diego.lara.di.modules

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.diego.lara.viewModel.TiendasViewModel
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class ViewModelModule {

    @Provides
    fun provideTiendassViewModel(fragmentActivity: FragmentActivity): TiendasViewModel {
        return ViewModelProviders.of(fragmentActivity).get(TiendasViewModel::class.java)
    }
}