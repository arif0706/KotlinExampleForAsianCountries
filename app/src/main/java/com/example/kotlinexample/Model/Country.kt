package com.example.kotlinexample.Model

import androidx.room.*
import com.example.kotlinexample.Model.Language
import com.example.kotlinexample.Room.ArrayTypeConvertor
import com.example.kotlinexample.Room.ByteConvertor
import com.example.kotlinexample.Room.LanguageTypeConvertor
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Countries",indices = [Index(unique = true,value = ["name"])])
class Country {

    @PrimaryKey(autoGenerate = true)
    var id:Int?=null

    @ColumnInfo(name="name")
    @SerializedName("name")
    var name:String?=null


    @ColumnInfo(name = "capital")
    @SerializedName("capital")
    var capital:String?=null


    @TypeConverters(ByteConvertor::class)
    @ColumnInfo(name = "flag",typeAffinity = ColumnInfo.BLOB)
    @SerializedName("flag")
    var flag:String? =null


    @ColumnInfo(name="region")
    @SerializedName("region")
    var region:String?=null

    @ColumnInfo(name="subregion")
    @SerializedName("subregion")
    var subregion:String?=null


    @ColumnInfo(name="population")
    @SerializedName("population")
    var population:String?=null

    @ColumnInfo(name="alpha3Code")
    @SerializedName("alpha3Code")
    var alpha3cCode:String?=null


    @TypeConverters(ArrayTypeConvertor::class)
    @ColumnInfo(name="borders")
    @SerializedName("borders")
    var borders:Array<String>?= arrayOf();


    @TypeConverters(LanguageTypeConvertor::class)
    @ColumnInfo(name="languages")
    @SerializedName("languages")
    var languages:Array<Language>?= arrayOf();

}
