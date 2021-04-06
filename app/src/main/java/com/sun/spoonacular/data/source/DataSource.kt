package com.sun.spoonacular.data.source

import com.sun.spoonacular.data.model.Recipe
import com.sun.spoonacular.data.model.RecipeDetail
import com.sun.spoonacular.data.model.RecipeResponse
import com.sun.spoonacular.data.model.SearchResponse
import retrofit2.Response

interface DataSource {

    interface Remote {
        suspend fun getRecipe(number: Int): Response<RecipeResponse>
        suspend fun getRecipeDetail(idRecipe: Int): Response<RecipeDetail>
        suspend fun getRecipesSimilar(idRecipe: Int): Response<List<Recipe>>
        suspend fun getRecipesByIngredient(nameIngredient: String): Response<List<Recipe>>
        suspend fun searchRecipeByName(nameRecipe: String, countRecipe: Int): Response<SearchResponse>
    }

    interface Local{}
}
