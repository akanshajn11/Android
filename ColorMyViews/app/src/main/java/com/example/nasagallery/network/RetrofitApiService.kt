package com.example.nasagallery.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://images-api.nasa.gov"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface RetrofitApiService {
    @GET("/search")
    fun getData(@Query("q") searchValue: String): Call<CollectionNasa>
}

object RetrofitApi {
    val retrofitService: RetrofitApiService by lazy {
        retrofit.create(RetrofitApiService::class.java)
    }
}