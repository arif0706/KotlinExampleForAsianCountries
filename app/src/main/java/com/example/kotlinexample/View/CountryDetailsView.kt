package com.example.kotlinexample.View

import com.example.kotlinexample.Model.Country

interface CountryDetailsView {
    fun onGettingCountry(country: Country?)
}