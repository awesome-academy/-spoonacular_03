package com.sun.spoonacular.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sun.spoonacular.R
import com.sun.spoonacular.data.model.Recipe
import com.sun.spoonacular.ui.home.recyclerview.RecipeDiffUtil
import com.sun.spoonacular.utils.Constant
import com.sun.spoonacular.utils.loadUrl
import kotlinx.android.synthetic.main.item_similar_recipe.view.*

class SearchRecipeAdapter(
    private val onItemClicked: (Recipe) -> Unit
) : ListAdapter<Recipe, SearchRecipeAdapter.RecipeByIngredientViewHolder>(RecipeDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecipeByIngredientViewHolder {
        return RecipeByIngredientViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_similar_recipe, parent, false), onItemClicked
        )
    }

    override fun onBindViewHolder(holder: RecipeByIngredientViewHolder, position: Int) {
        holder.bindData((getItem(position)))
    }

    class RecipeByIngredientViewHolder(
        itemView: View, private val onItemClicked: (Recipe) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bindData(recipe: Recipe) {
            itemView.apply {
                recipe.image?.let { imageSimilar.loadUrl(Constant.BASE_URL_IMAGE_RECIPE + it) }
                textTimeCook.text = context.getString(R.string.ready_in_minute, recipe.timeCook)
                textTitleDish.text = recipe.title
                setOnClickListener {
                    onItemClicked(recipe)
                }
            }
        }
    }
}
