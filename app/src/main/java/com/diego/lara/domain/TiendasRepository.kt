package com.diego.lara.domain

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.diego.lara.MainApplication
import com.diego.lara.R
import com.diego.lara.datasource.db.TiendasDiskDS
import com.diego.lara.datasource.web.TiendasWebDS
import com.diego.lara.di.components.DaggerDataSourceComponent
import com.diego.lara.di.modules.ContextModule
import com.diego.lara.models.Tiendas
import com.diego.lara.rest.models.OnTiendasResponse
import javax.inject.Inject

class TiendasRepository {

    @Inject
    lateinit var webDS: TiendasWebDS

    @Inject
    lateinit var diskDS: TiendasDiskDS

    init {
        DaggerDataSourceComponent.builder()
            .contextModule(ContextModule(MainApplication.appContext)).build().inject(this)
    }

    fun getData(
        name: String,
        onlyLocalSearch: Boolean,
        findMovieOnline: Boolean
    ): LiveData<List<Tiendas>> {
        when {
            findMovieOnline -> {
                return if (!MainApplication.appFeaturesProvider.hasInternetConnection()) {
                    showMessage(R.string.no_internet)
                    diskDS.readTiendas("%$name%")
                } else {
                    getFromNetwork(name, findMovieOnline)
                }
            }
            onlyLocalSearch -> {
                return diskDS.readTiendas("%$name%")
            }
            MainApplication.appFeaturesProvider.hasInternetConnection() -> {
                return getFromNetwork(name, findMovieOnline)
            }
            else -> {
                showMessage(R.string.no_internet)
                return diskDS.readTiendas("%$name%")
            }
        }
    }

    private fun showMessage(idMessage: Int) {
        Toast.makeText(
            MainApplication.appContext,
            MainApplication.appResourceProvider.getString(idMessage),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun getFromNetwork(
        query: String,
        findTiendaOnline: Boolean
    ): MutableLiveData<List<Tiendas>> {
        val tiendas = MutableLiveData<List<Tiendas>>()
            webDS.download(Observer {
                if (it !is OnTiendasResponse) {
                    return@Observer
                }
                setupDiskDs(it.tiendas, findTiendaOnline)
                tiendas.postValue(it.tiendas)
            })

        return tiendas
    }

    private fun setupDiskDs(movies: List<Tiendas>, findTiendaOnline: Boolean) {
        MainApplication.appExecutors.diskIO().execute {
            if (!findTiendaOnline) {
                diskDS.truncate()
            }
            diskDS.insertAll(movies)
        }
    }
}