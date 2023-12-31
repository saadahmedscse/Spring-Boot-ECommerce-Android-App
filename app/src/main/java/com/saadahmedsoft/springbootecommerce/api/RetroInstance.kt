package com.saadahmedsoft.springbootecommerce.api

import com.google.gson.GsonBuilder
import com.saadahmedsoft.springbootecommerce.utils.Constants.API.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroInstance {

    private val gson = GsonBuilder().setLenient().create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val instance: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}