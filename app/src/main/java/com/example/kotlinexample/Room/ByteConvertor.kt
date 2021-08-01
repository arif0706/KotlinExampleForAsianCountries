package com.example.kotlinexample.Room

import androidx.room.TypeConverter
class ByteConvertor {

    @TypeConverter
    fun ByteToString(byte: ByteArray):String{
        return byte.toString(Charsets.UTF_8)
    }

    @TypeConverter
    fun StringToByte(data:String): ByteArray {
        return data.toByteArray(Charsets.UTF_8)
    }


}