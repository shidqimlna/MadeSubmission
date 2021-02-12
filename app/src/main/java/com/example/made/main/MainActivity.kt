package com.example.made.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.made.R
import com.example.made.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.activityMainToolbar)

        val navController = findNavController(R.id.activity_main_navhost)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_popular,
                R.id.navigation_toprated,
                R.id.navigation_upcoming,
                R.id.navigation_search,
                R.id.navigation_favorite
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.activityMainNavbar.setupWithNavController(navController)
    }

}