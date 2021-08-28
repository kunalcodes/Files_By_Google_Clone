package com.example.google_files_app

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.google_files_app.Fragments.BrowseFragment
import com.example.google_files_app.Fragments.CleanFragment
import com.example.google_files_app.Fragments.ShareFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var selectedFragment: Fragment
    private var drawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)
        supportFragmentManager.beginTransaction().replace(R.id.container, CleanFragment())
            .commit()
        initViews()
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

    private fun initViews() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.Open_Drawer,
            R.string.Close_Drawer

        )
    }


    override fun onBackPressed() {
        supportFragmentManager.popBackStackImmediate()
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}