package com.diego.lara.rest.services

import com.diego.lara.rest.models.OnTiendasResponse
import com.diego.lara.rest.models.PayLoad
import com.diego.lara.utils.Constants.Web.API_URL
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface WebService {
    @POST(API_URL + "getConjuntotiendasUsuario")
    fun getTiendas(
        @Body payload: PayLoad
    ): Call<OnTiendasResponse>
}