package com.example.kotlinexample.View

import com.example.kotlinexample.Model.Country

interface MainActivityView {
    fun onGettingCountries(countries: List<Country>?)
    fun onGettingListFromRoom(countries: List<Country>?)
}