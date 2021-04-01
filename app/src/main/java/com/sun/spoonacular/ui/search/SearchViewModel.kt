package com.sun.spoonacular.ui.search

import androidx.lifecycle.MutableLiveData
import com.sun.spoonacular.data.model.SearchResponse
import com.sun.spoonacular.data.source.Repository
import com.sun.spoonacular.ui.base.BaseViewModel
import com.sun.spoonacular.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel : BaseViewModel() {

    private val repository = Repository.getInstance()
    private val _recipesAll = MutableLiveData<Resource<Response<SearchResponse>?>>()
    val recipeSearch: MutableLiveData<Resource<Response<SearchResponse>?>>
        get() = _recipesAll

    private val _recipeSearching = MutableLiveData<Resource<Response<SearchResponse>?>>()
    val recipeSearching: MutableLiveData<Resource<Response<SearchResponse>?>>
        get() = _recipeSearching

    fun fetchRecipeByName(nameRecipe: String, type: Int) {
        showLoading.postValue(true)
        countLoading++
        scope.launch {
            try {
                if (type == 0) {
                    _recipeSearching.postValue(
                        Resource.success(
                            repository.searchRecipeByName(
                                nameRecipe,
                                COUNT_SEARCHING
                            )
                        )
                    )
                } else {
                    _recipesAll.postValue(
                        Resource.success(
                            repository.searchRecipeByName(
                                nameRecipe,
                                COUNT_SEARCH
                            )
                        )
                    )
                }
            } catch (e: Exception) {
                exception.postValue(e)
                checkLoading()
            }
        }
    }

    companion object {
        const val COUNT_SEARCH = 30
        const val COUNT_SEARCHING = 5
    }
}
