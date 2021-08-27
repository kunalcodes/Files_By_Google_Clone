package com.example.google_files_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.google_files_app.Fragments.BrowseFragment
import com.example.google_files_app.Fragments.CleanFragment
import com.example.google_files_app.Fragments.ShareFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.google.android.material.tabs.TabLayoutMediator

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var selectedFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)
        supportFragmentManager.beginTransaction().replace(R.id.container, CleanFragment())
            .commit()
    }

    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

            selectedFragment = when (item.itemId) {
                R.id.nav_clean -> CleanFragment()
                R.id.nav_browse -> BrowseFragment()
                R.id.nav_share -> ShareFragment()
                else -> CleanFragment()
            }
            supportFragmentManager.beginTransaction().replace(R.id.container, selectedFragment)
                .commit()
            true
        }
}