package com.sun.spoonacular.ui.detail.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sun.spoonacular.R
import com.sun.spoonacular.data.model.Step
import kotlinx.android.synthetic.main.item_step_detail.view.*


class StepAdapter : ListAdapter<Step, StepAdapter.ItemStepViewHolder>(StepDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemStepViewHolder {
        return ItemStepViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_step_detail, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemStepViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class ItemStepViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bindData(steps: Step) {
            itemView.apply {
                textNumberStep.text = context.getString(R.string.step, steps.number.toString())
                textStep.text = context.getString(R.string.text_step, steps.step)
            }
        }
    }
}
