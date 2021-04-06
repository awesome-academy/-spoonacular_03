package com.sun.spoonacular.data.source.repository

import com.sun.spoonacular.data.source.DataSource
import com.sun.spoonacular.data.source.remote.RemoteDataSource

class SearchRepository private constructor(private val remote: DataSource.Remote) {

    suspend fun searchRecipeByName(nameRecipe: String, countRecipe: Int) =
        remote.searchRecipeByName(nameRecipe, countRecipe)

    companion object {
        private var instance: SearchRepository? = null

        fun getInstance() =
            instance ?: SearchRepository((RemoteDataSource.getInstance()))
                .also { instance = it }
    }
}
