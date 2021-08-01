package com.example.kotlinexample.Room

import androidx.room.TypeConverter
import com.example.kotlinexample.Model.Language
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LanguageTypeConvertor {
    val gson= Gson();
    @TypeConverter
    fun ArrayToList(data:Array<Language>):String{
        return gson.toJson(data)
    }

    @TypeConverter
    fun LanguageToArray(data:String):Array<Language>{
        val type=object:TypeToken<Array<Language>>(){}.type;
        return gson.fromJson(data,type)
    }

}