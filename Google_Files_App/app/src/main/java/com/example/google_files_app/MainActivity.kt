package com.example.google_files_app

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.google_files_app.Fragments.BrowseFragment
import com.example.google_files_app.Fragments.CleanFragment
import com.example.google_files_app.Fragments.ShareFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var selectedFragment: Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ivMenu.setOnClickListener(View.OnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        })
        ivBack.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)
        bottomNavigationView.selectedItemId = R.id.nav_browse
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, BrowseFragment(), "MY_FRAGMENT")
            .commit()

        val drawerNavigationView = findViewById<NavigationView>(R.id.nav_view)
        drawerNavigationView.setNavigationItemSelectedListener(this)
        drawerNavigationView.bringToFront()
        val toggle =
            ActionBarDrawerToggle(this, drawer_layout, R.string.Open_Drawer, R.string.Close_Drawer)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        changeToolbarsForSideActivities()
    }

    private fun changeToolbarsForSideActivities() {
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                layoutTopBarLandingPage.visibility = View.VISIBLE
                bottomNavigationWithShadow.visibility = View.VISIBLE
                layoutTopBarSideActivity.visibility = View.GONE

            } else if (supportFragmentManager.backStackEntryCount == 1) {
                layoutTopBarLandingPage.visibility = View.GONE
                bottomNavigationWithShadow.visibility = View.GONE
                layoutTopBarSideActivity.visibility = View.VISIBLE
            }
        }
    }


    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_browse -> ivSearch.visibility = View.VISIBLE
                else -> ivSearch.visibility = View.GONE
            }
            selectedFragment = when (item.itemId) {
                R.id.nav_clean -> CleanFragment()
                R.id.nav_browse -> BrowseFragment()
                R.id.nav_share -> ShareFragment()
                else -> CleanFragment()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, selectedFragment, "MY_FRAGMENT")
                .commit()
            true
        }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show()
            R.id.help -> Toast.makeText(this, "help", Toast.LENGTH_SHORT).show()
            R.id.trash -> Toast.makeText(this, "trash", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        layoutTopBarLandingPage.visibility = View.VISIBLE
        bottomNavigationWithShadow.visibility = View.VISIBLE
        layoutTopBarSideActivity.visibility = View.GONE
    }

}