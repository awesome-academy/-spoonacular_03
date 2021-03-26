package com.sun.spoonacular.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sun.spoonacular.R
import com.sun.spoonacular.data.model.Recipe
import com.sun.spoonacular.ui.base.MainFragment
import com.sun.spoonacular.utils.*

class SplashFragment : Fragment() {

    private var recipeSlides = arrayListOf<Recipe>()
    private var recipeRecyclerViews = arrayListOf<Recipe>()

    private val splashViewModel by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
    }

    private fun registerObservers() {
        splashViewModel.apply {
            recipeSlide.observe(viewLifecycleOwner, {
                it?.let {
                    when (it.status) {
                        Status.SUCCESS -> recipeSlides =
                            it.data?.body()?.recipes as ArrayList<Recipe>
                        Status.ERROR -> Toast.makeText(
                            context, exception.toString(), Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })

            recipeRecyclerView.observe(viewLifecycleOwner, {
                it?.let {
                    when (it.status) {
                        Status.SUCCESS -> recipeRecyclerViews =
                            it.data?.body()?.recipes as ArrayList<Recipe>
                        Status.ERROR -> Toast.makeText(
                            context, exception.toString(), Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })

            showLoading.observe(viewLifecycleOwner, {
                if (!it) {
                    replaceFragmentNoStack(
                        MainFragment.newInstance(recipeSlides, recipeRecyclerViews),
                        R.id.mainContainer
                    )
                }
            })
        }
    }

    companion object {
        fun newInstance() = SplashFragment()
    }
}
