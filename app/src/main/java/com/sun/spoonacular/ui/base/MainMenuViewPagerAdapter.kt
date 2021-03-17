package com.sun.spoonacular.ui.base

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sun.spoonacular.ui.favourite.FavouriteFragment
import com.sun.spoonacular.ui.home.HomeFragment
import com.sun.spoonacular.ui.search.SearchFragment
import com.sun.spoonacular.utils.MenuNumber

class MainMenuViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount() = MENU_COUNT

    override fun getItem(position: Int) = when (position) {
        MenuNumber.SEARCH.pageNumber -> SearchFragment.newInstance()
        MenuNumber.HOME.pageNumber -> HomeFragment.newInstance()
        else -> FavouriteFragment.newInstance()
    }

    companion object {
        const val MENU_COUNT = 3
    }
}
