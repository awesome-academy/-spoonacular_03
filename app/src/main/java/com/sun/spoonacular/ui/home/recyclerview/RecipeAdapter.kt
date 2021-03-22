package com.sun.spoonacular.ui.home.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sun.spoonacular.R
import com.sun.spoonacular.data.model.Recipe

class RecipeAdapter(private val onItemClicked: (Recipe) -> Unit) :
    ListAdapter<Recipe, RecipeViewHolder>(RecipeDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recipe_home, parent, false)
        view.layoutParams.width = (parent.width * 0.87).toInt()
        return RecipeViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.binData(getItem(position))
    }
}
