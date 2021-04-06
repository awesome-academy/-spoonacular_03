package com.sun.spoonacular.ui.ingredient

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sun.spoonacular.R
import com.sun.spoonacular.data.model.Ingredient
import com.sun.spoonacular.ui.detail.DetailRecipeFragment
import com.sun.spoonacular.ui.ingredient.adapter.RecipeByIngredientAdapter
import com.sun.spoonacular.utils.Constant
import com.sun.spoonacular.utils.addFragment
import com.sun.spoonacular.utils.loadUrl
import kotlinx.android.synthetic.main.fragment_ingredient_detail.*
import kotlinx.android.synthetic.main.fragment_ingredient_detail.imgBack
import kotlinx.android.synthetic.main.fragment_ingredient_detail.viewLoading

class IngredientDetailFragment : Fragment() {

    private var isLoading = false

    private val ingredientDetailViewModel by lazy {
        ViewModelProvider(this).get(IngredientDetailViewModel::class.java)
    }

    private val adapterRecipe by lazy {
        RecipeByIngredientAdapter {
            addFragment(DetailRecipeFragment.newInstance(it.id), R.id.mainContainer)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ingredient_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgIngredient.loadUrl(
            Constant.BASE_URL_IMAGE_INGREDIENT + arguments?.get(BUNDLE_IMAGE_INGREDIENT)
        )
        textNameIngredient.text = arguments?.get(BUNDLE_NAME_INGREDIENT).toString()
        imgBack.setOnClickListener {
            fragmentManager?.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        initView()
        registerObservers()
    }

    private fun initView() {
        recyclerIngredientDetail.apply {
            adapter = adapterRecipe
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val linearLayoutManager = (layoutManager as LinearLayoutManager)
                    if (!isLoading) {
                        ingredientDetailViewModel.apply {
                            if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == recipes.value?.count()
                                    ?.minus(1) ?: 0
                            ) {
                                progressBarLoadMore.visibility = View.VISIBLE
                                val handlerSlider = Looper.myLooper()?.let { Handler(it) }
                                handlerSlider?.postDelayed({
                                    loadMoreData()
                                    progressBarLoadMore.visibility = View.GONE
                                }, DELAY_LOAD_MORE)
                            }
                            isLoadMore.observe(viewLifecycleOwner, {
                                isLoading = if (it == true) {
                                    ingredientDetailViewModel.loadMoreData()
                                    true
                                } else false
                            })
                        }
                    }
                }
            })
        }
    }

    private fun registerObservers() {
        ingredientDetailViewModel.apply {
            fetchRecipeByIngredient(
                arguments?.getString(
                    BUNDLE_NAME_INGREDIENT
                ) ?: ""
            )

            exception.observe(viewLifecycleOwner, {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            })

            recipes.observe(viewLifecycleOwner, {
                adapterRecipe.submitList(it)
            })

            showLoading.observe(viewLifecycleOwner, {
                if (!it) {
                    viewLoading.visibility = View.GONE
                }
            })

            recipesAll.observe(viewLifecycleOwner, {
                if (it.size >= 5) {
                    recipes.postValue(it.subList(0, 5))
                } else recipes.postValue(it)
                showLoading.postValue(false)
            })
        }
    }

    companion object {
        private const val BUNDLE_NAME_INGREDIENT = "BUNDLE_NAME_INGREDIENT"
        private const val BUNDLE_IMAGE_INGREDIENT = "BUNDLE_IMAGE_INGREDIENT"
        private const val DELAY_LOAD_MORE = 500L

        fun newInstance(ingredient: Ingredient?) = IngredientDetailFragment().apply {
            arguments = bundleOf(
                BUNDLE_NAME_INGREDIENT to ingredient?.name,
                BUNDLE_IMAGE_INGREDIENT to ingredient?.image
            )
        }
    }
}
