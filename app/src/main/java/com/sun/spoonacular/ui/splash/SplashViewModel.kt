package com.sun.spoonacular.ui.splash

import androidx.lifecycle.*
import com.sun.spoonacular.data.model.RecipeResponse
import com.sun.spoonacular.data.source.Repository
import com.sun.spoonacular.ui.base.BaseViewModel
import com.sun.spoonacular.utils.Resource
import kotlinx.coroutines.*
import retrofit2.Response

class SplashViewModel : BaseViewModel() {

    private val repository = Repository.getInstance()

    private val _recipeSlide = MutableLiveData<Resource<Response<RecipeResponse>>>()
    val recipeSlide: MutableLiveData<Resource<Response<RecipeResponse>>>
        get() = _recipeSlide

    private val _recipe = MutableLiveData<Resource<Response<RecipeResponse>>>()
    val recipeRecyclerView: MutableLiveData<Resource<Response<RecipeResponse>>>
        get() = _recipe

    init {
        countLoading = 2
        fetchRecipesListFive()
        fetchRecipesListTen()
    }

    private fun fetchRecipesListFive() {
        showLoading.postValue(true)
        scope.launch {
            try {
                recipeSlide.postValue(Resource.success(repository.getRecipe(NUMBER_COUNT_RECIPE_5)))
                checkLoading()
            } catch (e: Exception) {
                exception.postValue(e)
                checkLoading()
            }
        }
    }

    private fun fetchRecipesListTen() {
        scope.launch {
            try {
                recipeRecyclerView.postValue(
                    Resource.success(
                        repository.getRecipe(
                            NUMBER_COUNT_RECIPE_10
                        )
                    )
                )
                checkLoading()
            } catch (e: Exception) {
                exception.postValue(e)
                checkLoading()
            }
        }
    }

    companion object {
        private const val NUMBER_COUNT_RECIPE_5 = 5
        private const val NUMBER_COUNT_RECIPE_10 = 10
    }
}
