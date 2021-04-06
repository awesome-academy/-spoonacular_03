package com.sun.spoonacular.utils

import com.sun.spoonacular.BuildConfig

object Constant {
    const val BASE_URL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/"
    const val API_KEY = "x-rapidapi-key"
    const val API_VALUE = BuildConfig.API_KEY
    const val HOST_KEY = "x-rapidapi-host"
    const val HOST_VALUE = "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com"
    const val BASE_URL_IMAGE_RECIPE = "https://spoonacular.com/recipeImages/"
    const val IMAGE_RECIPE_SIMILAR = "-556x370.jpg"
    const val BASE_URL_IMAGE_INGREDIENT = "https://spoonacular.com/cdn/ingredients_100x100/"
    const val SCALE_WIDTH_WITH_PAREN = 0.87
    const val DATABASE_NAME = "recipe_favourite.db"
    const val FAVOURITE_RECIPE_TABLE_NAME = "recipe_favourite_table"
}
