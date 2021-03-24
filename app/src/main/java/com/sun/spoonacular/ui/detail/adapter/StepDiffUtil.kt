package com.sun.spoonacular.ui.detail.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sun.spoonacular.data.model.Step

class StepDiffUtil : DiffUtil.ItemCallback<Step>() {

    override fun areItemsTheSame(oldItem: Step, newItem: Step): Boolean {
        return oldItem.number == newItem.number
    }

    override fun areContentsTheSame(oldItem: Step, newItem: Step): Boolean {
        return oldItem == newItem
    }
}
