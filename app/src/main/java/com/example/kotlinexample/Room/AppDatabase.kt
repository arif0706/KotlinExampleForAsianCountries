package com.example.kotlinexample.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinexample.Model.Country

@Database(entities = [Country::class] , version = 1)
abstract  class AppDatabase: RoomDatabase() {

    companion object {
        private const val DB_NAME = "Countries"
        private var instance: AppDatabase? = null

        @Synchronized
        fun getRoomInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance !!
        }
    }
    abstract fun databaseDao():DatabaseDao;
}