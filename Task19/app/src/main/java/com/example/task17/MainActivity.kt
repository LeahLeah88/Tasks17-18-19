package com.example.task17

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.task17.databinding.ActivityMainBinding
import com.example.task17.ui.DetailFragment
import com.example.task17.ui.ListFragment
import com.example.task17.ui.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            openFragment(SearchFragment())
        }

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_filter -> {
                    openFragment(SearchFragment())
                    true
                }

                R.id.nav_list -> {
                    openFragment(ListFragment())
                    true
                }

                R.id.nav_detail -> {
                    openFragment(DetailFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun openFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
