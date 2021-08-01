package com.example.kotlinexample.Controller

import com.example.kotlinexample.Api.RetrofitClient
import com.example.kotlinexample.Model.Country
import com.example.kotlinexample.View.CountryDetailsView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryDetailsController(val countryDetailsView: CountryDetailsView):ICountryDetailsController {

    override fun getCountryWithCode(code: String) {
        val call=RetrofitClient().getRetrofitInstance().getMyApi().getCountryWithCode(code)
        call.enqueue(object: Callback<Country>{
            override fun onResponse(call: Call<Country>, response: Response<Country>) {
                countryDetailsView.onGettingCountry(response.body())
            }

            override fun onFailure(call: Call<Country>, t: Throwable) {

            }

        })
    }
}