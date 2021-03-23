package com.sun.spoonacular.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sun.spoonacular.R

fun ImageView.loadUrl(imageApi: String) {
    Glide.with(context)
        .load(imageApi)
        .error(R.drawable.null_recipe)
        .placeholder(R.drawable.null_recipe)
        .into(this)
}
