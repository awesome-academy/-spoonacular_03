package com.sun.spoonacular.data.source

import com.sun.spoonacular.data.model.RecipeResponse
import retrofit2.Response

interface DataSource {

    interface Remote {
        suspend fun getRecipe(): Response<RecipeResponse>
        suspend fun getRecipes(): Response<RecipeResponse>
    }
}
