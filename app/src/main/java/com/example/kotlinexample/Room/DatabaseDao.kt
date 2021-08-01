package com.example.kotlinexample.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinexample.Model.Country


@Dao
interface DatabaseDao {

    @Query("Select distinct *from Countries")
    fun getCountries():List<Country>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertCountry(country: Country)

    @Query("Select distinct *from Countries where alpha3Code=:code")
    fun getCountry(code:String):Country

    @Query("Delete from countries")
    fun deleteData()
}