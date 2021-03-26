package com.sun.spoonacular.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sun.spoonacular.ui.detail.DetailRecipeViewModel

class ViewModelFactory(private val param: Any) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailRecipeViewModel(param as Int) as T
    }
}
