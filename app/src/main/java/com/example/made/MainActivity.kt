package com.example.made

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.made.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.activityMainToolbar)

        binding.activityMainViewpager.adapter = PagerAdapter(this, supportFragmentManager)
        binding.activityMainTablayout.setupWithViewPager(binding.activityMainViewpager)
    }

}