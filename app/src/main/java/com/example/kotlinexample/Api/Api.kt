package com.example.kotlinexample.Api

import com.example.kotlinexample.Model.Country
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/rest/v2/region/Asia")
    fun getCountries(): Call<List<Country>>

    @GET("/rest/v2/alpha/{code}")
    fun getCountryWithCode(@Path("code") code: String): Call<Country>
}