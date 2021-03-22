package com.sun.spoonacular.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sun.spoonacular.data.model.RecipeResponse
import com.sun.spoonacular.data.source.Repository
import com.sun.spoonacular.utils.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

class SplashViewModel : ViewModel() {

    private val repository = Repository.getInstance()
    fun getRecipe() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data =  arrayListOf(repository.getRecipe(),repository.getRecipes())))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }
}
