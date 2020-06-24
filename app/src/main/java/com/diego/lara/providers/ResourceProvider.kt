package com.diego.lara.providers

import android.content.Context
import javax.inject.Inject

class ResourceProvider @Inject constructor(private val context: Context) {
    fun getString(id: Int): String {
        return context.getString(id)
    }

    fun getColor(id: Int): Int {
        return context.resources.getColor(id)
    }

}