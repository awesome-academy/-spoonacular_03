package com.sun.spoonacular.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sun.spoonacular.R
<<<<<<< Updated upstream
import com.sun.spoonacular.ui.base.MainFragment
import com.sun.spoonacular.utils.*
import kotlinx.android.synthetic.main.fragment_splash.*
=======
>>>>>>> Stashed changes

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

<<<<<<< Updated upstream
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
    }

    private fun registerObservers() {
        splashViewModel.getRecipe().observe(viewLifecycleOwner, { userList ->
            userList?.let { it ->
                when (it.status) {
                    Status.SUCCESS -> {
                        Toast.makeText(
                            context,
                            it.data?.body()?.recipes?.size.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        replaceFragmentNoStack(MainFragment.newInstance(), R.id.mainContainer)
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

=======
>>>>>>> Stashed changes
    companion object {
        fun newInstance() = SplashFragment()
    }
}
