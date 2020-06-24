package com.diego.lara.datasource.web

import androidx.lifecycle.Observer
import com.diego.lara.di.components.DaggerFrameworkComponent
import com.diego.lara.rest.models.PayLoad
import com.diego.lara.rest.models.Proyecto
import com.diego.lara.rest.models.Usuario
import com.diego.lara.rest.services.WebService
import com.diego.lara.rest.utils.ApiCallback
import javax.inject.Inject

class TiendasWebDS {
    @Inject
    lateinit var webService: WebService

    init {
        DaggerFrameworkComponent.builder().build().inject(this)
    }

    fun download(
        observer: Observer<Any>
    ) {
        webService.getTiendas(
            PayLoad(
                Usuario("11208"),
                Proyecto("137", 0)
            )
        ).enqueue(ApiCallback(observer))
    }
}