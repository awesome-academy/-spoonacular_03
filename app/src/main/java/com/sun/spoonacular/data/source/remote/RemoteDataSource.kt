package com.sun.spoonacular.data.source.remote

import com.sun.spoonacular.data.model.RecipeResponse
import com.sun.spoonacular.data.source.DataSource
import com.sun.spoonacular.data.source.remote.api.ApiClient
import retrofit2.Response

class RemoteDataSource private constructor() : DataSource.Remote {

    override suspend fun getRecipe(): Response<RecipeResponse> {
        return ApiClient.apiService.getRecipe()
    }

    companion object {
        private var instance: RemoteDataSource? = null
        fun getInstance() = instance ?: RemoteDataSource().also { instance = it }
    }
}
