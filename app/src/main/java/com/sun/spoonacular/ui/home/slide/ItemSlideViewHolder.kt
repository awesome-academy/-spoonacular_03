package com.sun.spoonacular.ui.home.slide

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sun.spoonacular.R
import com.sun.spoonacular.data.model.Recipe
import kotlinx.android.synthetic.main.item_slide_home.view.*

class ItemSlideViewHolder(itemView: View, private val onSlideClicked: (Recipe) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    fun binData(recipeSlide: Recipe) {
        itemView.apply {
            Glide.with(context)
                .load(recipeSlide.image)
                .into(imageSlide)
            textTitleDish.text = recipeSlide.title
            textTimeCook.text = context.getString(R.string.ready_in_minute, recipeSlide.timeCook)
            setOnClickListener {
                onSlideClicked.invoke(recipeSlide)
            }
        }
    }
}
