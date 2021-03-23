package com.sun.spoonacular.ui.home.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sun.spoonacular.R
import com.sun.spoonacular.data.model.Recipe
import com.sun.spoonacular.utils.loadUrl
import kotlinx.android.synthetic.main.item_recipe_home.view.*


class RecipeViewHolder(
    itemView: View,
    private val onItemClicked: (Recipe) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun binData(recipe: Recipe) {
        itemView.apply {
            imgDish.loadUrl(recipe.image.toString())
            textTitleDish.text = recipe.title
            textTimeCook.text = context.getString(R.string.ready_in_minute, recipe.timeCook)
            textScore.text = context.getString(R.string.spoonacular_score, recipe.score.toString())
            setOnClickListener {
                onItemClicked(recipe)
            }
        }
    }
}
