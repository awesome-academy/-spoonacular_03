package com.sun.spoonacular.data.source.remote

import com.sun.spoonacular.data.model.Recipe
import com.sun.spoonacular.data.model.RecipeDetail
import com.sun.spoonacular.data.model.RecipeResponse
import com.sun.spoonacular.data.model.SearchResponse
import com.sun.spoonacular.data.source.DataSource
import com.sun.spoonacular.data.source.remote.api.ApiClient
import retrofit2.Response

class RemoteDataSource private constructor() : DataSource.Remote {

    override suspend fun getRecipe(number: Int): Response<RecipeResponse> {
        return ApiClient.apiService.getRecipe(number)
    }

    override suspend fun getRecipeDetail(idRecipe: Int): Response<RecipeDetail> {
        return ApiClient.apiService.getRecipeDetail(idRecipe)
    }

    override suspend fun getRecipesSimilar(idRecipe: Int): Response<List<Recipe>> {
        return ApiClient.apiService.getRecipeSimilar(idRecipe)
    }

    override suspend fun getRecipesByIngredient(nameIngredient: String): Response<List<Recipe>> {
        return ApiClient.apiService.getRecipeByIngredient(nameIngredient)
    }

    override suspend fun searchRecipeByName(
        nameRecipe: String, countRecipe: Int
    ): Response<SearchResponse> {
        return ApiClient.apiService.searchRecipeByName(nameRecipe, countRecipe)
    }

    companion object {
        private var instance: RemoteDataSource? = null
        fun getInstance() = instance ?: RemoteDataSource().also { instance = it }
    }
}
