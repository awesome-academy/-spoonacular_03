package com.sun.spoonacular.data.source

import androidx.lifecycle.LiveData
import com.sun.spoonacular.data.model.*
import retrofit2.Response

interface DataSource {

    interface Remote {
        suspend fun getRecipe(number: Int): Response<RecipeResponse>

        suspend fun getRecipeDetail(idRecipe: Int): Response<RecipeDetail>

        suspend fun getRecipesSimilar(idRecipe: Int): Response<List<Recipe>>

        suspend fun getRecipesByIngredient(nameIngredient: String): Response<List<Recipe>>

        suspend fun searchRecipeByName(nameRecipe: String, countRecipe: Int): Response<SearchResponse>
    }

    interface Local {
        fun getRecipe(): LiveData<List<RecipeFavourite>>

        suspend fun insertRecipe(recipe: RecipeFavourite)

        suspend fun deleteRecipe(idRecipe: Int)

        suspend fun findAlreadyExists(idRecipe: Int): List<RecipeFavourite>
    }
}
