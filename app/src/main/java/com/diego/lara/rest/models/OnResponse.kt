package com.diego.lara.rest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Response

/**
 * FIXED OnResponse only working for current app, try to use a different app as reference of onResponse
 */
open class OnResponse {
    @Expose
    var status = 0

    @Expose
    var message: String

    @get:Deprecated("")
    @SerializedName("estatus")
    @Expose
    var serviceStatus: String? = null

    @get:Deprecated("")
    @SerializedName("descripcion")
    @Expose
    var serviceDescription: String? = null

    constructor(status: Int, message: String) {
        this.status = status
        this.message = message
    }

    @Deprecated("")
    constructor(serviceStatus: String?, serviceDescription: String) {
        this.serviceStatus = serviceStatus
        this.serviceDescription = serviceDescription
        message = serviceDescription
    }

    internal constructor(response: Response<*>) {
        status = response.code()
        message = response.message()
    }

    constructor() {
        status = 0
        message = ""
    }
}