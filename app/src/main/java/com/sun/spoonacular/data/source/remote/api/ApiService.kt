package com.sun.spoonacular.data.source.remote.api

import com.sun.spoonacular.data.model.Recipe
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

    @GET("{ids}/similar")
    suspend fun getRecipeSimilar(
        @Path("ids") id: Int,
    ): Response<List<Recipe>>

    @GET("findByIngredients")
    suspend fun getRecipeByIngredient(
        @Query("ingredients") name: String,
        @Query("number") number: Int = 50,
        @Query("ranking") ranking: Int = 1,
        @Query("ignorePantry") ignorePantry: Boolean = true
    ): Response<List<Recipe>>
}
