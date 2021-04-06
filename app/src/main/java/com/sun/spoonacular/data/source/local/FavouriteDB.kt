package com.sun.spoonacular.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sun.spoonacular.data.model.RecipeFavourite

@Database(entities = [RecipeFavourite::class], version = 1)
abstract class FavouriteDB : RoomDatabase() {

    abstract fun favouriteDAO(): DAO
}
