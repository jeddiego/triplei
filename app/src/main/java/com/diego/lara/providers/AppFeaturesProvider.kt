package com.diego.lara.providers

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class AppFeaturesProvider @Inject constructor(private val context: Context) {
    fun hasInternetConnection(): Boolean {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected
    }

}