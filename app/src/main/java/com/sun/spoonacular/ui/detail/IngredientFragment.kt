package com.sun.spoonacular.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sun.spoonacular.R
import com.sun.spoonacular.data.model.Ingredient
import com.sun.spoonacular.ui.detail.adapter.IngredientAdapter
import com.sun.spoonacular.ui.ingredient.IngredientDetailFragment
import com.sun.spoonacular.utils.addFragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet_ingredient.*

@Suppress("UNCHECKED_CAST")
class IngredientFragment : BottomSheetDialogFragment() {

    private var ingredients = listOf<Ingredient>()
    private val adapter by lazy {
        IngredientAdapter {
            (activity as? AppCompatActivity)?.addFragment(
                IngredientDetailFragment.newInstance(it),
                R.id.mainContainer
            )
            dialog?.dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_ingredient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewIngredient()
    }

    private fun initRecyclerViewIngredient() {
        ingredients = arguments?.get(BUNDLE_LIST_INGREDIENT) as List<Ingredient>
        adapter.submitList(ingredients)
        recyclerViewBottomSheet.adapter = adapter
    }

    companion object {

        private const val BUNDLE_LIST_INGREDIENT = "BUNDLE_LIST_INGREDIENT"

        fun newInstance(listIngredient: List<Ingredient>) = IngredientFragment().apply {
            arguments = bundleOf((BUNDLE_LIST_INGREDIENT to listIngredient))
        }
    }
}
