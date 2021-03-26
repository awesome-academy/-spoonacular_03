package com.sun.spoonacular.ui.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sun.spoonacular.R
import com.sun.spoonacular.data.model.Ingredient
import com.sun.spoonacular.utils.Constant
import com.sun.spoonacular.utils.loadUrl
import kotlinx.android.synthetic.main.item_ingredient.view.*

class IngredientAdapter(private val onItemClicked: (Ingredient) -> Unit) :
    ListAdapter<Ingredient, IngredientAdapter.ItemIngredientViewHolder>(IngredientDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemIngredientViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false)
        return ItemIngredientViewHolder(itemView, onItemClicked)
    }

    override fun onBindViewHolder(holder: ItemIngredientViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class ItemIngredientViewHolder(
        itemView: View,
        private val onItemClicked: (Ingredient) -> Unit
    ) :
        RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindData(ingredient: Ingredient) {
            itemView.apply {
                imgIngredient.loadUrl(Constant.BASE_URL_IMAGE_INGREDIENT + ingredient.image)
                textIngredient.text = ingredient.name
                textAmount.text = ingredient.amount.toString() + ingredient.unit
                setOnClickListener {
                    onItemClicked(ingredient)
                }
            }
        }
    }
}
