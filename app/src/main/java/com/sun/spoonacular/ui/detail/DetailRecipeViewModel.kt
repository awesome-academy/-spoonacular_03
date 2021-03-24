package com.sun.spoonacular.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.spoonacular.data.source.Repository
import com.sun.spoonacular.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class DetailRecipeViewModel : ViewModel() {

    private val recipeDetail = MutableLiveData<Resource<MutableList<Any>>>()
    private val repository = Repository.getInstance()

    fun fetchRecipeDetail(id: Int) {
        viewModelScope.launch {
            recipeDetail.postValue(Resource.loading(null))
            try {
                coroutineScope {
                    val recipeInfoDeferred = async { repository.getRecipeDetail(id) }
                    val recipeSimilarDeferred = async { repository.getRecipesSimilar(id) }

                    val recipeInfo = recipeInfoDeferred.await()
                    val recipeSimilar = recipeSimilarDeferred.await()

                    val bigRecipeDetail = mutableListOf<Any>()

                    recipeInfo.body()?.let { bigRecipeDetail.add(it) }
                    recipeSimilar.body()?.let { bigRecipeDetail.add(it) }

                    recipeDetail.postValue(Resource.success(bigRecipeDetail))
                }
            } catch (e: Exception) {
                recipeDetail.postValue(Resource.error(null, e.toString()))
            }
        }
    }

    fun getRecipeDetail() = recipeDetail
}
