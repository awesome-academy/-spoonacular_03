package com.sun.spoonacular.data.source.repository

import com.sun.spoonacular.data.source.DataSource
import com.sun.spoonacular.data.source.remote.RemoteDataSource

class RecipeDetailRepository private constructor(private val remote: DataSource.Remote) {

    suspend fun getRecipeDetail(idRecipe: Int) = remote.getRecipeDetail(idRecipe)

    suspend fun getRecipesSimilar(idRecipe: Int) = remote.getRecipesSimilar(idRecipe)

    companion object {
        private var instance: RecipeDetailRepository? = null

        fun getInstance() =
            instance
                ?: RecipeDetailRepository(RemoteDataSource.getInstance())
                    .also { instance = it }
    }
}
