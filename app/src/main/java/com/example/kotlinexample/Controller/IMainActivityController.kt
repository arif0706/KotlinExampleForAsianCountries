package com.example.kotlinexample.Controller

import android.content.Context

interface IMainActivityController {

    fun getCountries()
    fun getCountriesFromRoom(context: Context)
}