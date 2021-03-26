package com.sun.spoonacular.ui.detail

import androidx.lifecycle.MutableLiveData
import com.sun.spoonacular.data.model.Recipe
import com.sun.spoonacular.data.model.RecipeDetail
import com.sun.spoonacular.data.source.Repository
import com.sun.spoonacular.ui.base.BaseViewModel
import com.sun.spoonacular.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailRecipeViewModel(idRecipe: Int) : BaseViewModel() {

    private val repository = Repository.getInstance()

    private val _recipeInfo = MutableLiveData<Resource<Response<RecipeDetail>>>()
    val recipeInfo: MutableLiveData<Resource<Response<RecipeDetail>>>
        get() = _recipeInfo

    private val _recipeSimilar = MutableLiveData<Resource<Response<List<Recipe>>>>()
    val recipeSimilar: MutableLiveData<Resource<Response<List<Recipe>>>>
        get() = _recipeSimilar

    init {
        countLoading = 2
        fetchRecipeInfo(idRecipe)
        fetchRecipeSimilar(idRecipe)
    }

    private fun fetchRecipeInfo(id: Int) {
        showLoading.postValue(true)
        scope.launch {
            try {
                recipeInfo.postValue(Resource.success(repository.getRecipeDetail(id)))
                checkLoading()
            } catch (e: Exception) {
                exception.postValue(e)
                checkLoading()
            }
        }
    }

    private fun fetchRecipeSimilar(id: Int) {
        showLoading.postValue(true)
        scope.launch {
            try {
                recipeSimilar.postValue(Resource.success(repository.getRecipesSimilar(id)))
                checkLoading()
            } catch (e: Exception) {
                exception.postValue(e)
                checkLoading()
            }
        }
    }
}
