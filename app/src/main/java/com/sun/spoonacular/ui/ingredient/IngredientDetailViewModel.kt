package com.sun.spoonacular.ui.ingredient

import androidx.lifecycle.MutableLiveData
import com.sun.spoonacular.data.model.Recipe
import com.sun.spoonacular.data.source.repository.RecipeByIngredientRepository
import com.sun.spoonacular.ui.base.BaseViewModel
import com.sun.spoonacular.utils.Resource
import com.sun.spoonacular.utils.safeLet
import kotlinx.coroutines.*

class IngredientDetailViewModel : BaseViewModel() {

    private val repository = RecipeByIngredientRepository.getInstance()
    val recipes = MutableLiveData<MutableList<Recipe?>>()
    val recipesAll: MutableLiveData<MutableList<Recipe?>>
        get() = _recipesAll
    private val _recipesAll = MutableLiveData<MutableList<Recipe?>>()
    var isLoadMore = MutableLiveData<Boolean>()

    fun loadMoreData() {
        safeLet(recipesAll.value, recipes.value) { recipeAll, recipe ->
            if (recipeAll.size > recipe.size) {
                if (recipeAll.size % DEFAULT_LOAD == 0) this.recipes.postValue(
                    (recipe + recipeAll.subList(
                        recipe.size, recipe.size + DEFAULT_LOAD
                    )
                            ) as MutableList<Recipe?>?
                )
                else this.recipes.postValue(
                    (recipe + recipeAll.subList(
                        recipe.size, recipe.size + (recipeAll.size % DEFAULT_LOAD)
                    )
                            ) as MutableList<Recipe?>?
                )
                isLoadMore.postValue(true)
            }
            isLoadMore.postValue(false)
        }
    }

    fun fetchRecipeByIngredient(nameIngredient: String) {
        showLoading.postValue(true)
        scope.launch {
            try {
                _recipesAll.postValue(
                    Resource.success(repository.getRecipesByIngredient(nameIngredient)).data?.body()
                        ?.toMutableList()
                )
                checkLoading()
            } catch (e: Exception) {
                exception.postValue(e)
                checkLoading()
            }
        }
    }

    companion object {
        const val DEFAULT_LOAD = 5
    }
}
