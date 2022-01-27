package com.uguraltintas.rickandmorty.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.uguraltintas.rickandmorty.R
import com.uguraltintas.rickandmorty.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigation = binding.bottomNavigationView


        NavigationUI.setupWithNavController(bottomNavigation, navController)

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.topAppBar.setupWithNavController(navController, appBarConfiguration)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.characterDetailFragment, R.id.episodeDetailFragment, R.id.locationDetailFragment -> {
                    (binding.topAppBar.layoutParams as AppBarLayout.LayoutParams).scrollFlags = 0
                }
                else -> {
                    (binding.topAppBar.layoutParams as AppBarLayout.LayoutParams)
                        .scrollFlags = (AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                            or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS)
                }
            }

        }

        bottomNavigation.setBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                R.color.dark_gray,
                null
            )
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() ||
                super.onSupportNavigateUp()
    }
}