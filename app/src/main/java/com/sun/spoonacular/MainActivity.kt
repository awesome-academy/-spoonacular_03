package com.sun.spoonacular

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.sun.spoonacular.ui.base.MainFragment
import com.sun.spoonacular.ui.splash.SplashFragment
import com.sun.spoonacular.utils.addFragment

class MainActivity : AppCompatActivity() {

    private var isDoubleBackPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(SplashFragment.newInstance(),R.id.mainContainer)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else if (!isDoubleBackPressed) {
            this.isDoubleBackPressed = true
            Toast.makeText(this, R.string.back_again, Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed({ isDoubleBackPressed = false }, DELAY_TIME)
            return
        } else {
            super.onBackPressed()
            return
        }
    }

    companion object {
        const val DELAY_TIME = 2000L
    }
}
