package com.sun.spoonacular.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.sun.spoonacular.R
import com.sun.spoonacular.utils.Status

class DetailRecipeFragment : Fragment() {

    private val detailViewModel by lazy {
        ViewModelProvider(this).get(DetailRecipeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
    }

    private fun registerObservers() {
        detailViewModel.fetchRecipeDetail(arguments?.get(BUNDLE_ID_RECIPE) as Int)
        detailViewModel.getRecipeDetail()
            .observe(viewLifecycleOwner, {
                it?.let {
                    when (it.status) {
                        Status.SUCCESS -> {
                            Toast.makeText(context, it.data?.get(0).toString(), Toast.LENGTH_SHORT)
                                .show()
                            Toast.makeText(context, it.data?.get(1).toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                        }
                    }
                }
            })
    }

    companion object {
        private const val BUNDLE_ID_RECIPE = "BUNDLE_ID_RECIPE"

        fun newInstance(idRecipe: Int?) = DetailRecipeFragment().apply {
            arguments = bundleOf(BUNDLE_ID_RECIPE to idRecipe)
        }
    }
}
