package com.sun.spoonacular.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.sun.spoonacular.R

class DetailRecipeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    companion object {
        private const val BUNDLE_ID_RECIPE = "BUNDLE_ID_RECIPE"

        fun newInstance(idRecipe: Int?) = DetailRecipeFragment().apply {
            arguments = bundleOf(BUNDLE_ID_RECIPE to idRecipe)
        }
    }
}
