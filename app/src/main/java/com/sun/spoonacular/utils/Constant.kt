package com.sun.spoonacular.utils

import com.sun.spoonacular.BuildConfig

object Constant {
    const val BASE_URL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/"
    const val RANDOM_5 = "random?number=5"
    const val RANDOM_10 = "random?number=10"
    const val API_KEY = "x-rapidapi-key"
    const val API_VALUE = BuildConfig.API_KEY
    const val HOST_KEY = "x-rapidapi-host"
    const val HOST_VALUE = "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com"
}
