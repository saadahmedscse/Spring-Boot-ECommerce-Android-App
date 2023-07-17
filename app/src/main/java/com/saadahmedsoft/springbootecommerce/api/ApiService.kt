package com.saadahmedsoft.springbootecommerce.api

import com.google.gson.JsonObject
import com.saadahmedsoft.springbootecommerce.services.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("login")
    fun login(@Body jsonObject: JsonObject): Call<LoginResponse>
}