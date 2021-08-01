package com.example.kotlinexample.Room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
class ArrayTypeConvertor {

    val gson:Gson= Gson()

    @TypeConverter
    fun ArrayToList(data:Array<String>):String{
        return gson.toJson(data)
    }

    @TypeConverter
    fun StringToArray(data:String):Array<String>{
        val type=object :TypeToken<Array<String>>(){}.type
        return gson.fromJson(data,type)
    }
}