package com.sun.spoonacular.data.source.repository

import android.content.Context
import com.sun.spoonacular.data.model.RecipeFavourite
import com.sun.spoonacular.data.source.DataSource
import com.sun.spoonacular.data.source.local.LocalDataSource

class FavouriteRepository private constructor(private val local: DataSource.Local) {

    fun getRecipeDB() = local.getRecipe()

    suspend fun insertRecipe(recipe: RecipeFavourite) = local.insertRecipe(recipe)

    suspend fun deleteRecipe(idRecipe: Int) = local.deleteRecipe(idRecipe)

    suspend fun findAlreadyExists(idRecipe: Int) = local.findAlreadyExists(idRecipe)

    companion object {
        private var instance: FavouriteRepository? = null

        fun getInstance(context: Context) =
            instance ?: LocalDataSource.getInstance(context)?.let { local ->
                FavouriteRepository(local).also { instance = it }
            }
    }
}
