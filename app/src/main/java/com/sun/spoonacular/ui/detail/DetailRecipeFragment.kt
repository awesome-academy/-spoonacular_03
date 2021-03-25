package com.sun.spoonacular.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.sun.spoonacular.R
import com.sun.spoonacular.data.model.*
import com.sun.spoonacular.ui.detail.adapter.RecipeSimilarAdapter
import com.sun.spoonacular.ui.detail.adapter.StepAdapter
import com.sun.spoonacular.utils.Status
import com.sun.spoonacular.utils.addFragment
import com.sun.spoonacular.utils.loadUrl
import kotlinx.android.synthetic.main.fragment_detail.*

@Suppress("UNCHECKED_CAST")
class DetailRecipeFragment : Fragment() {

    private var recipeDetail: RecipeDetail? = null
    private var recipesSimilar = mutableListOf<Recipe>()

    private val stepAdapter by lazy { StepAdapter() }
    private val adapter by lazy {
        RecipeSimilarAdapter {
            (activity as? AppCompatActivity)?.addFragment(
                newInstance(it.id),
                R.id.mainContainer
            )
        }
    }
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

    private fun initView() {
        imgBack.setOnClickListener {
            fragmentManager?.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        recipeDetail?.image?.let { imageDish.loadUrl(it) }
        textTime.text =
            context?.getString(R.string.ready_in_minute, recipeDetail?.timeCook?.toInt().toString())
        textTitleDish.text = recipeDetail?.title
        try {
            stepAdapter.submitList(recipeDetail?.step?.get(0)?.steps)
        } catch (e: Exception) {
            e.printStackTrace()
            textStepError.visibility = View.VISIBLE
        }
        viewPagerStep.adapter = stepAdapter
        adapter.submitList(recipesSimilar)
        recyclerViewSimilarRecipe.adapter = adapter
    }

    private fun registerObservers() {
        detailViewModel.fetchRecipeDetail(arguments?.get(BUNDLE_ID_RECIPE) as Int)
        detailViewModel.getRecipeDetail()
            .observe(viewLifecycleOwner, {
                it?.let {
                    when (it.status) {
                        Status.SUCCESS -> {
                            recipeDetail = it.data?.first() as RecipeDetail
                            recipesSimilar = it.data[1] as MutableList<Recipe>
                            initView()
                            viewLoading.visibility = View.GONE
                        }
                        Status.ERROR -> {
                            viewLoading.visibility = View.GONE
                            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            viewLoading.visibility = View.VISIBLE
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
