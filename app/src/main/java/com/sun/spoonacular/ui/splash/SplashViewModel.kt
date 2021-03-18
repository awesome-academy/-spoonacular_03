package com.sun.spoonacular.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sun.spoonacular.data.source.Repository
import com.sun.spoonacular.utils.Resource
import kotlinx.coroutines.Dispatchers

class SplashViewModel : ViewModel() {

    private val repository = Repository.getInstance()

    fun getRecipe() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getRecipe()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }
}
