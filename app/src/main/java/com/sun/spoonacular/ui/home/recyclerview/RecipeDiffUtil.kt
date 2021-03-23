package com.sun.spoonacular.ui.home.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.sun.spoonacular.data.model.Recipe

class RecipeDiffUtil : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }
}
