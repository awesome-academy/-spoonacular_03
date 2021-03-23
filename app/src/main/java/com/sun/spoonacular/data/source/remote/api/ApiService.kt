package com.sun.spoonacular.data.source.remote.api

import com.sun.spoonacular.data.model.RecipeDetail
import com.sun.spoonacular.data.model.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("random")
    suspend fun getRecipe(@Query("number") number: Int): Response<RecipeResponse>

    @GET("{ids}/information")
    suspend fun getRecipeDetail(
        @Path("ids") id: Int,
        @Query("includeNutrition") number: Boolean = true
    ): Response<RecipeDetail>
}
