package com.sun.spoonacular.ui.detail.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sun.spoonacular.data.model.Ingredient

class IngredientDiffUtil : DiffUtil.ItemCallback<Ingredient>() {

    override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem == newItem
    }
}
