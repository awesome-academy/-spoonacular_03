package com.sun.spoonacular.data.source.local

import android.content.Context
import androidx.room.Room
import com.sun.spoonacular.utils.Constant

object DatabaseBuilder {

    private var instance: FavouriteDB? = null

    private const val DATABASE_NAME = Constant.DATABASE_NAME

    fun getInstance(context: Context): FavouriteDB? {
        return if (instance == null)
            Room.databaseBuilder(context, FavouriteDB::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
        else instance
    }
}
