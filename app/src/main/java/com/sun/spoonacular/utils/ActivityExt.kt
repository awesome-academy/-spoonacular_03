package com.sun.spoonacular.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.addFragmentNoStack(fragment: Fragment, id: Int) {
    supportFragmentManager.inTransaction { add(id, fragment) }
}

fun AppCompatActivity.addFragment(fragment: Fragment, id: Int) {
    supportFragmentManager.inTransaction { add(id, fragment).addToBackStack(null) }
}
