package com.sun.spoonacular.ui.search

import androidx.lifecycle.MutableLiveData
import com.sun.spoonacular.data.model.Recipe
import com.sun.spoonacular.data.model.SearchResponse
import com.sun.spoonacular.data.source.repository.SearchRepository
import com.sun.spoonacular.ui.base.BaseViewModel
import com.sun.spoonacular.utils.Resource
import com.sun.spoonacular.utils.safeLet
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel : BaseViewModel() {

    var isLoadMore = MutableLiveData<Boolean>()

    val recipes = MutableLiveData<MutableList<Recipe?>>()
    val textSearching = MutableLiveData<String>()

    private val repository = SearchRepository.getInstance()
    private val _recipesAll = MutableLiveData<Resource<Response<SearchResponse>?>>()
    private val _recipeSearching = MutableLiveData<Resource<Response<SearchResponse>?>>()

    val recipeSearch: MutableLiveData<Resource<Response<SearchResponse>?>>
        get() = _recipesAll
    val recipeSearching: MutableLiveData<Resource<Response<SearchResponse>?>>
        get() = _recipeSearching

    fun loadMoreData() {
        safeLet(recipeSearch.value?.data?.body()?.recipes, recipes.value) { recipeSearch, recipe ->
            if (recipeSearch.size > recipe.size) {
                if (recipeSearch.size % DEFAULT_LOAD == 0) this.recipes.postValue(
                    (recipe + recipeSearch.subList(
                        recipe.size, recipe.size + DEFAULT_LOAD
                    )) as MutableList<Recipe?>?
                )
                else this.recipes.postValue(
                    (recipe + recipeSearch.subList(
                        recipe.size,
                        recipe.size + (recipeSearch.size % DEFAULT_LOAD)
                    )
                            ) as MutableList<Recipe?>?
                )
                isLoadMore.postValue(true)
            }
            isLoadMore.postValue(false)
        }
    }

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
        const val DEFAULT_LOAD = 5
    }
}
