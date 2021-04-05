package com.sun.spoonacular.ui.search.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sun.spoonacular.R
import com.sun.spoonacular.data.model.Recipe
import com.sun.spoonacular.ui.home.recyclerview.RecipeDiffUtil
import kotlinx.android.synthetic.main.item_searching.view.*

class SearchingAdapter(
    private val onItemClicked: (Recipe) -> Unit
) : ListAdapter<Recipe, SearchingAdapter.ItemSearchingViewHolder>(RecipeDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ItemSearchingViewHolder {
        return ItemSearchingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_searching, parent, false), onItemClicked
        )
    }

    override fun onBindViewHolder(holder: ItemSearchingViewHolder, position: Int) {
        holder.bindData((getItem(position)))
    }

    class ItemSearchingViewHolder(
        itemView: View, private val onItemClicked: (Recipe) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bindData(recipe: Recipe) {
            itemView.apply {
                textNameRecipe.text = recipe.title
                setOnClickListener {
                    onItemClicked(recipe)
                }
            }
        }
    }
}
