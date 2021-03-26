package com.sun.spoonacular.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sun.spoonacular.R
import com.sun.spoonacular.data.model.Nutrient
import com.sun.spoonacular.ui.detail.adapter.NutrientAdapter
import kotlinx.android.synthetic.main.fragment_bottom_sheet_nutrient.*

@Suppress("UNCHECKED_CAST")
class NutrientFragment : BottomSheetDialogFragment() {

    private var nutrients = listOf<Nutrient>()
    private val adapter by lazy {
        NutrientAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_nutrient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewNutrient()
    }

    private fun initRecyclerViewNutrient() {
        nutrients = arguments?.get(BUNDLE_LIST_NUTRIENT) as List<Nutrient>
        adapter.submitList(nutrients)
        recyclerViewBottomSheetNutrient.adapter = adapter
    }

    companion object {

        private const val BUNDLE_LIST_NUTRIENT = "BUNDLE_LIST_NUTRIENT"

        fun newInstance(nutrients: List<Nutrient>?) = NutrientFragment().apply {
            arguments = bundleOf(BUNDLE_LIST_NUTRIENT to nutrients)
        }
    }
}
