package com.sun.spoonacular.ui.detail.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sun.spoonacular.data.model.Nutrient

class NutrientDiffUtil : DiffUtil.ItemCallback<Nutrient>() {

    override fun areItemsTheSame(oldItem: Nutrient, newItem: Nutrient): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Nutrient, newItem: Nutrient): Boolean {
        return oldItem == newItem
    }
}
