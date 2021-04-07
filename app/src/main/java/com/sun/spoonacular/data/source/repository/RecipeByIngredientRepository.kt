package com.sun.spoonacular.data.source.repository

import com.sun.spoonacular.data.source.DataSource
import com.sun.spoonacular.data.source.remote.RemoteDataSource

class RecipeByIngredientRepository private constructor(private val remote: DataSource.Remote) {

    suspend fun getRecipesByIngredient(nameIngredient: String) =
        remote.getRecipesByIngredient(nameIngredient)

    companion object {
        private var instance: RecipeByIngredientRepository? = null

        fun getInstance() =
            instance ?: RecipeByIngredientRepository(RemoteDataSource.getInstance())
                .also { instance = it }
    }
}
