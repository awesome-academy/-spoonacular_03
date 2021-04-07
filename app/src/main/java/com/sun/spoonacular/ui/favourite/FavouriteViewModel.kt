package com.sun.spoonacular.ui.favourite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sun.spoonacular.data.model.RecipeFavourite
import com.sun.spoonacular.data.source.repository.FavouriteRepository
import kotlinx.coroutines.launch

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {

    private val favouriteRepository = FavouriteRepository.getInstance(application)

    val recipes: LiveData<List<RecipeFavourite>>? = favouriteRepository?.getRecipeDB()
    val isFavourite = MutableLiveData<Boolean>()

    fun addRecipe(recipe: RecipeFavourite) {
        viewModelScope.launch {
            try {
                favouriteRepository?.insertRecipe(recipe)
                isFavourite.postValue(true)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteRecipe(recipeId: Int) {
        viewModelScope.launch {
            try {
                favouriteRepository?.deleteRecipe(recipeId)
                isFavourite.postValue(false)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun findExisted(idRecipe: Int) {
        viewModelScope.launch {
            try {
                if (favouriteRepository?.findAlreadyExists(idRecipe).isNullOrEmpty()) {
                    isFavourite.postValue(false)
                } else isFavourite.postValue(true)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
