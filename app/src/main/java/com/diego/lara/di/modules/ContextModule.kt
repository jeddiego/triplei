package com.diego.lara.di.modules

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides

@Module
class ContextModule {
    private lateinit var fragmentActivity: FragmentActivity
    private lateinit var fragment: Fragment
    private lateinit var context: Context

    constructor(fragmentActivity: FragmentActivity) {
        this.fragmentActivity = fragmentActivity
    }

    constructor(fragment: Fragment) {
        this.fragment = fragment
    }

    constructor(context: Context) {
        this.context = context
    }

    @Provides
    fun provideFragmentActivity(): FragmentActivity {
        return fragmentActivity
    }

    @Provides
    fun provideFragment(): Fragment {
        return fragment
    }

    @Provides
    fun provideContext(): Context {
        return context
    }
}