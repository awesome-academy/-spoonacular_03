package com.sun.spoonacular.ui.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sun.spoonacular.R
import com.sun.spoonacular.data.model.Nutrient
import kotlinx.android.synthetic.main.item_nutrient.view.*

class NutrientAdapter :
    ListAdapter<Nutrient, NutrientAdapter.ItemNutrientViewHolder>(NutrientDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemNutrientViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_nutrient, parent, false)
        return ItemNutrientViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemNutrientViewHolder, position: Int) {
        holder.binData(getItem(position))
    }

    class ItemNutrientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun binData(nutrient: Nutrient) {
            itemView.apply {
                textNutrient.text = nutrient.name
                textAmount.text = nutrient.amount.toString() + nutrient.unit
            }
        }
    }
}
