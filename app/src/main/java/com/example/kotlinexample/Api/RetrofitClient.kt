package com.example.kotlinexample.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(
    private val BASE_URL:String="https://restcountries.eu/",
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
        .build(),
    private val myApi: Api =retrofit.create(Api::class.java),
) {

    fun getRetrofitInstance(): RetrofitClient {
        return RetrofitClient()
    }
    @JvmName("getMyApi1")
    fun getMyApi(): Api {
        return myApi
    }

}