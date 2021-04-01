package com.sun.spoonacular.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.sun.spoonacular.R
import com.sun.spoonacular.data.model.Recipe
import com.sun.spoonacular.utils.Status
import com.sun.spoonacular.utils.hideKeyboard
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private var recipeSearching = mutableListOf<Recipe>()

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnClickListener { hideKeyboard() }
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        setUpSearchView()
        registerObserve()
    }

    private fun setUpSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.fetchRecipeByName(query.toString(), TYPE_SEARCH)
                viewLoadingSearchPage.visibility = View.VISIBLE
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchViewModel.fetchRecipeByName(newText, TYPE_SEARCHING)
                }
                return true
            }
        })
    }

    private fun registerObserve() {
        searchViewModel.apply {
            recipeSearching.observe(viewLifecycleOwner, {
                this@SearchFragment.recipeSearching =
                    it.data?.body()?.recipes as MutableList<Recipe>
            })
            recipeSearch.observe(viewLifecycleOwner, {
                if (it.status == Status.SUCCESS) {
                    viewLoadingSearchPage.visibility = View.GONE
                }
            })
        }
    }

    companion object {
        const val TYPE_SEARCH = 1
        const val TYPE_SEARCHING = 0

        fun newInstance() = SearchFragment()
    }
}
