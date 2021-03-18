package com.sun.spoonacular.data.source.remote.api

import com.sun.spoonacular.data.model.RecipeResponse
import com.sun.spoonacular.utils.Constant
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constant.RANDOM)
    suspend fun getRecipe() : Response<RecipeResponse>
}
