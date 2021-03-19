package com.sun.spoonacular.data.source.remote

import com.sun.spoonacular.data.model.RecipeResponse
import com.sun.spoonacular.data.source.DataSource
import com.sun.spoonacular.data.source.remote.api.ApiClient
import retrofit2.Response

class RemoteDataSource : DataSource.Remote {

    override suspend fun getRecipe(): Response<RecipeResponse> {
        return ApiClient.apiService.getRecipe()
    }

    companion object {
        fun newInstance() = RemoteDataSource()
    }
}
