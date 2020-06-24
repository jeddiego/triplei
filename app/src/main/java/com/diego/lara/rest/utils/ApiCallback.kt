package com.diego.lara.rest.utils

import android.util.Log
import androidx.lifecycle.Observer
import com.diego.lara.MainApplication
import com.diego.lara.R
import com.diego.lara.rest.models.OnResponse
import com.diego.lara.rest.models.OnTiendasResponse
import com.diego.lara.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

class ApiCallback<T : OnTiendasResponse>(private val observer: Observer<Any>) :
    Callback<T> {
    override fun onResponse(
        call: Call<T>,
        response: Response<T>
    ) {
        MainApplication.appExecutors.diskIO().execute {
            if (response.body() == null) {
                observer.onChanged(
                    OnResponse(
                        response.code(),
                        String.format(
                            MainApplication.appResourceProvider
                                .getString(R.string.retrofit_error_unproccess),
                            response.message()
                        )
                    )
                )
                return@execute
            }
            if (response.code() == 200) {
                observer.onChanged(response.body())
            }
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        MainApplication.appExecutors.diskIO().execute {
            val response = OnResponse()
            response.status = Constants.Web.HTTP_RETROFIT_ERROR
            Log.e("onFailure", "Throwable: " + t.message)
            if (!MainApplication.appFeaturesProvider.hasInternetConnection()) {
                response.message =
                    MainApplication.appResourceProvider.getString(R.string.no_internet)
            } else {
                when (t) {
                    is SocketTimeoutException -> {
                        response.message = MainApplication.appResourceProvider
                            .getString(R.string.retrofit_error_timeout)
                    }
                    is IOException -> {
                        response.message = String.format(
                            MainApplication.appResourceProvider
                                .getString(R.string.retrofit_error_server),
                            if (t.message!!.contains("timeout")) MainApplication.appResourceProvider
                                .getString(R.string.retrofit_error_timeout) else t.message
                        )
                    }
                    else -> {
                        response.message = MainApplication.appResourceProvider
                            .getString(R.string.retrofit_error_body)
                    }
                }
            }
            observer.onChanged(response)
        }
    }

}