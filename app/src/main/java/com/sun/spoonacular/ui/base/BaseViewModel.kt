package com.sun.spoonacular.ui.base

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.plus

open class BaseViewModel : ViewModel() {

    val exception = MutableLiveData<Throwable>()
    val showLoading = MutableLiveData<Boolean>()
    val scope = viewModelScope.plus(CoroutineExceptionHandler { _, throwable ->
        exception.postValue(throwable)
    })

    var countLoading = 0

    fun checkLoading() {
        countLoading--
        if (countLoading == 0) showLoading.postValue(false)
    }
}
