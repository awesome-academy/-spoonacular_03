package com.sun.spoonacular.ui.detail.adapter

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

class RecipeSimilarAdapter(private val onItemClicked: (Recipe) -> Unit) :
    ListAdapter<Recipe, RecipeSimilarAdapter.RecipeViewHolder>(RecipeDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_similar_recipe, parent, false)
        view.layoutParams.width = (parent.width * Constant.SCALE_WIDTH_WITH_PAREN).toInt()
        return RecipeViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.binData(getItem(position))
    }

    class RecipeViewHolder(
        itemView: View,
        private val onItemClicked: (Recipe) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun binData(recipe: Recipe) {
            itemView.apply {
                imageSimilar.loadUrl(Constant.BASE_URL_IMAGE_RECIPE + recipe.id + Constant.IMAGE_RECIPE_SIMILAR)
                textTitleDish.text = recipe.title
                textTimeCook.text = context.getString(R.string.ready_in_minute, recipe.timeCook)
                setOnClickListener {
                    onItemClicked(recipe)
                }
            }
        }
    }
}
