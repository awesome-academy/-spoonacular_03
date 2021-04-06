package com.sun.spoonacular.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sun.spoonacular.data.model.RecipeFavourite
import com.sun.spoonacular.utils.Constant

@Dao
interface DAO {
    @Insert
    fun insertRecipe(recipe: RecipeFavourite)

    @Query("SELECT * FROM ${Constant.FAVOURITE_RECIPE_TABLE_NAME}")
    fun getAllRecipe(): LiveData<List<RecipeFavourite>>

    @Query("SELECT * FROM ${Constant.FAVOURITE_RECIPE_TABLE_NAME} WHERE id= :id")
    fun findAlreadyExists(id: Int): List<RecipeFavourite>

    @Query("DELETE FROM ${Constant.FAVOURITE_RECIPE_TABLE_NAME} WHERE id = :id")
    fun deleteRecipe(id: Int)
}
