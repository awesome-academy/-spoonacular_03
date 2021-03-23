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
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment : Fragment() {

    private var recipeSlide = arrayListOf<Recipe>()
    private var recipeRecyclerView = arrayListOf<Recipe>()

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
            getRecipe().observe(viewLifecycleOwner, {
                it?.let {
                    when (it.status) {
                        Status.SUCCESS -> {
                            recipeSlide = it.data?.get(0)?.body()?.recipes as ArrayList<Recipe>
                            recipeRecyclerView = it.data[1].body()?.recipes as ArrayList<Recipe>
                            replaceFragmentNoStack(
                                MainFragment.newInstance(
                                    recipeSlide,
                                    recipeRecyclerView
                                ),
                                R.id.mainContainer
                            )
                        }
                        Status.ERROR -> {
                            progressBar.visibility = View.GONE
                            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                        }
                    }
                }
            })
        }
    }

    companion object {
        fun newInstance() = SplashFragment()
    }
}
