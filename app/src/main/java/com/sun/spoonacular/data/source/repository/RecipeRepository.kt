package com.sun.spoonacular.data.source.repository

import com.sun.spoonacular.data.source.DataSource
import com.sun.spoonacular.data.source.remote.RemoteDataSource

class RecipeRepository private constructor(private val remote: DataSource.Remote) {

    suspend fun getRecipe(number: Int) = remote.getRecipe(number)


    companion object {
        private var instance: RecipeRepository? = null

        fun getInstance() =
            instance ?: RecipeRepository(RemoteDataSource.getInstance())
                .also { instance = it }
    }
}
