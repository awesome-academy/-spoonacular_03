package com.sun.spoonacular.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun AppCompatActivity.addFragment(fragment: Fragment, id: Int) {
    supportFragmentManager.inTransaction { add(id, fragment) }
}

fun AppCompatActivity.addFragmentF(fragment: Fragment,id: Int){
    supportFragmentManager.inTransaction { add(id, fragment).addToBackStack(null) }
}

fun Fragment.addFragment(fragment: Fragment, id: Int) {
    fragmentManager?.inTransaction { add(id, fragment).addToBackStack(null) }
}
fun Fragment.replaceFragment(fragment: Fragment, id: Int) {
    fragmentManager?.inTransaction { replace(id, fragment).addToBackStack(null) }
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
