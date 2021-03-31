package com.sun.spoonacular.ui.ingredient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sun.spoonacular.R
import com.sun.spoonacular.data.model.Recipe
import com.sun.spoonacular.ui.home.recyclerview.RecipeDiffUtil
import com.sun.spoonacular.utils.loadUrl
import kotlinx.android.synthetic.main.item_similar_recipe.view.*

class RecipeByIngredientAdapter(
    private val onItemClicked: (Recipe) -> Unit
) : ListAdapter<Recipe, RecipeByIngredientAdapter.RecipeByIngredientViewHolder>(RecipeDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecipeByIngredientViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe_by_ingredient, parent, false)
        return RecipeByIngredientViewHolder(itemView, onItemClicked)
    }

    class RecipeByIngredientViewHolder(
        itemView: View, private val onItemClicked: (Recipe) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bindData(recipe: Recipe) {
            itemView.apply {
                recipe.image?.let { imageSimilar.loadUrl(it) }
                textTitleDish.text = recipe.title
                setOnClickListener {
                    onItemClicked(recipe)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecipeByIngredientViewHolder, position: Int) {
        holder.bindData((getItem(position)))
    }
}
