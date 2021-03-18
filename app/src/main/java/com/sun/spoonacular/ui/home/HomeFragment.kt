package com.sun.spoonacular.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.sun.spoonacular.R
import com.sun.spoonacular.data.model.Recipe
import com.sun.spoonacular.ui.detail.DetailRecipeFragment
import com.sun.spoonacular.ui.home.slide.HomeSlideAdapter
import com.sun.spoonacular.utils.addFragment
import kotlinx.android.synthetic.main.fragment_home.*

@Suppress("UNCHECKED_CAST")
class HomeFragment : Fragment() {

    private var recipeSlide = arrayListOf<Recipe>()
    private var getCurrentItem = 0
    private var isChecked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDataHome()
    }

    private fun initDataHome() {
        recipeSlide = (arguments?.get(BUNDLE_LIST_RECIPE_KEY) as? ArrayList<Recipe>) ?: ArrayList()
        setUpViewPager(viewPagerSlideHome)
    }

    private fun setUpViewPager(applyViewPageSlider: ViewPager2) {
        applyViewPageSlider.apply {
            adapter = HomeSlideAdapter(recipeSlide) {
                (activity as? AppCompatActivity)?.addFragment(
                    DetailRecipeFragment.newInstance(it.id),
                    R.id.mainContainer
                )
            }
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(GET_ITEM_START).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    val handlerSlider = Looper.myLooper()?.let { Handler(it) }
                    super.onPageSelected(position)
                    handlerSlider?.postDelayed({
                        when (getCurrentItem) {
                            (recipeSlide.size.minus(ITEM_NUMBER)) -> isChecked = false
                            GET_ITEM_START -> isChecked = true
                        }
                        getCurrentItem =
                            if (isChecked) getCurrentItem + ITEM_NUMBER else getCurrentItem - ITEM_NUMBER
                        viewPagerSlideHome?.currentItem = getCurrentItem
                    }, DELAY_NEXT_ITEM)
                }
            })
        }
    }

    companion object {
        private const val DELAY_NEXT_ITEM = 2000L
        private const val GET_ITEM_START = 0
        private const val ITEM_NUMBER = 1
        private const val BUNDLE_LIST_RECIPE_KEY = "BUNDLE_LIST_RECIPE_KEY"

        fun newInstance(recipes: ArrayList<Recipe>) = HomeFragment().apply {
            arguments = bundleOf(BUNDLE_LIST_RECIPE_KEY to recipes)
        }
    }
}
