package com.sun.spoonacular.ui.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sun.spoonacular.R
import com.sun.spoonacular.data.model.Recipe
import com.sun.spoonacular.ui.detail.DetailRecipeFragment
import com.sun.spoonacular.ui.search.adapter.SearchRecipeAdapter
import com.sun.spoonacular.ui.search.adapter.SearchingAdapter
import com.sun.spoonacular.utils.Status
import com.sun.spoonacular.utils.addFragment
import com.sun.spoonacular.utils.hideKeyboard
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchingAdapter: SearchingAdapter
    private lateinit var searchAdapter: SearchRecipeAdapter

    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        initAdapter()
        setUpSearchView()
        initView()
        registerObserve()
        view.setOnClickListener { hideKeyboard() }
    }

    private fun addDetailFragment(recipe: Recipe) {
        hideKeyboard()
        (activity as? AppCompatActivity)?.addFragment(
            DetailRecipeFragment.newInstance(recipe.id),
            R.id.mainContainer
        )
    }

    private fun initAdapter() {
        searchAdapter = SearchRecipeAdapter { addDetailFragment(it) }
        searchingAdapter = SearchingAdapter { addDetailFragment(it) }
    }

    private fun initView() {
        recyclerViewSearching.adapter = searchingAdapter
        recyclerViewSearch.apply {
            adapter = searchAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val linearLayoutManager = (layoutManager as LinearLayoutManager)
                    if (!isLoading) {
                        searchViewModel.apply {
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
                                    searchViewModel.loadMoreData()
                                    true
                                } else false
                            })
                        }
                    }
                }
            })
        }
        textSearch.setOnClickListener {
            viewLoadingSearchPage.visibility = View.VISIBLE
            searchViewModel.textSearching.value?.let { textSearch ->
                searchViewModel.fetchRecipeByName(textSearch.trim(), TYPE_SEARCH)
            }
            viewSearching.visibility = View.GONE
        }
    }

    private fun setUpSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.fetchRecipeByName(query?.trim().toString(), TYPE_SEARCH)
                viewLoadingSearchPage.visibility = View.VISIBLE
                viewSearching.visibility = View.GONE
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchViewModel.apply {
                        textSearching.postValue(newText)
                        if (newText.count() > START_SEARCHING) {
                            fetchRecipeByName(newText.toString(), TYPE_SEARCHING)
                            viewSearching.visibility = View.VISIBLE
                        }
                    }
                } else {
                    textSearch.visibility = View.GONE
                }
                return true
            }
        })
    }

    private fun registerObserve() {
        searchViewModel.apply {
            recipeSearching.observe(viewLifecycleOwner, {
                if (it.status == Status.SUCCESS) {
                    viewLoading.visibility = View.GONE
                    searchingAdapter.submitList(it.data?.body()?.recipes)
                }
            })

            exception.observe(viewLifecycleOwner, {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            })

            recipeSearch.observe(viewLifecycleOwner, {
                if (it.status == Status.SUCCESS) {
                    if (it?.data?.body()?.recipes?.size!! >= 5) {
                        recipes.postValue(it.data.body()?.recipes?.subList(0, 5)?.toMutableList())
                    } else {
                        recipes.postValue(it.data.body()?.recipes!!.toMutableList())
                    }
                    hideKeyboard()
                    viewLoadingSearchPage.visibility = View.GONE
                }
            })

            recipes.observe(viewLifecycleOwner, {
                searchAdapter.submitList(it)
            })

            textSearching.observe(viewLifecycleOwner, {
                textSearch.text = context?.getString(R.string.see_result_for, it)
            })

            showLoading.observe(viewLifecycleOwner, {
                if (it == true) {
                    viewLoading.visibility = View.VISIBLE
                } else viewLoading.visibility = View.GONE
            })
        }
    }

    companion object {
        const val TYPE_SEARCH = 1
        const val TYPE_SEARCHING = 0
        const val START_SEARCHING = 3
        private const val DELAY_LOAD_MORE = 700L

        fun newInstance() = SearchFragment()
    }
}
