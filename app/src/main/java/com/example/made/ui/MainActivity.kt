package com.example.made.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.made.R
import com.example.made.core.ui.PagerAdapter
import com.example.made.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

//    private val fragmentManager: FragmentManager = supportFragmentManager
//    private val movieFragment: HomeFragment = HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.activityMainToolbar)
        supportActionBar?.elevation = 0f

        binding.activityMainViewpager.adapter = PagerAdapter(this, supportFragmentManager)
        binding.activityMainTablayout.setupWithViewPager(binding.activityMainViewpager)
//        if (savedInstanceState == null) {
//            activity_main_bottomnavigationbar.setItemSelected(R.id.menu_movie, true)
//            movieFragment.let {
//                fragmentManager.beginTransaction().replace(
//                    R.id.activity_main_fragmentcontainer,
//                    it
//                )
//                    .commit()
//            }
//        }
//        activity_main_bottomnavigationbar.setOnItemSelectedListener(object :
//            ChipNavigationBar.OnItemSelectedListener {
//            override fun onItemSelected(id: Int) {
//                var fragment: Fragment? = null
//                when (id) {
//                    R.id.menu_movie -> fragment = HomeFragment()
//                    R.id.menu_tvshow -> fragment = TvShowFragment()
//                }
//                if (fragment != null) {
//                    val fragmentTransaction = fragmentManager.beginTransaction()
//                    fragmentTransaction.let {
//                        it.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
//                        it.replace(R.id.activity_main_fragmentcontainer, fragment)
//                        it.commit()
//                    }
//                }
//            }
//        })
    }
}