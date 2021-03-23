package com.sun.spoonacular.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.spoonacular.data.model.RecipeDetail
import com.sun.spoonacular.data.source.Repository
import com.sun.spoonacular.utils.Resource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class DetailRecipeViewModel : ViewModel() {

    private val recipeDetail = MutableLiveData<Resource<RecipeDetail>>()
    private val repository = Repository.getInstance()

    fun fetchRecipeDetail(id: Int) {
        viewModelScope.launch {
            recipeDetail.postValue(Resource.loading(null))
            try {
                coroutineScope {
                    recipeDetail.postValue(
                        Resource.success(
                            repository.getRecipeDetail(id).body()
                        ) as Resource<RecipeDetail>?
                    )
                }
            } catch (e: Exception) {
                recipeDetail.postValue(Resource.error(null, e.toString()))
            }
        }
    }

    fun getRecipeDetail() = recipeDetail
}
