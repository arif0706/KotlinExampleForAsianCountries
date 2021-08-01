package com.example.kotlinexample.Controller

import android.content.Context
import com.example.kotlinexample.Api.RetrofitClient
import com.example.kotlinexample.Model.Country
import com.example.kotlinexample.Room.AppDatabase
import com.example.kotlinexample.View.MainActivityView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityController(var view:MainActivityView):IMainActivityController {
    override fun getCountries() {
        val call=RetrofitClient().getRetrofitInstance().getMyApi().getCountries();
        call.enqueue(object : Callback<List<Country>>{
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                   view.onGettingCountries(response.body())
            }
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                println(t.localizedMessage)
            }
        })
    }

    override fun getCountriesFromRoom(context: Context) {
        val appdatabase=AppDatabase.getRoomInstance(context);
        val countries:List<Country> = appdatabase.databaseDao().getCountries();
        view.onGettingListFromRoom(countries)
    }
}