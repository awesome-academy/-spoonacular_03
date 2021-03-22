package com.sun.spoonacular.ui.home.slide

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.spoonacular.R
import com.sun.spoonacular.data.model.Recipe

class HomeSlideAdapter(
    private val slides: MutableList<Recipe>,
    private val onSlideClicked: (Recipe) -> Unit
) : RecyclerView.Adapter<ItemSlideViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSlideViewHolder {
        return ItemSlideViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_slide_home, parent, false),
            onSlideClicked
        )
    }

    override fun onBindViewHolder(holder: ItemSlideViewHolder, position: Int) {
        holder.binData(slides[position])
    }

    override fun getItemCount() = slides.size
}
