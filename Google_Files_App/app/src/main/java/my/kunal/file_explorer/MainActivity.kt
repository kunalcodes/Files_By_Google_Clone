package my.kunal.file_explorer

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import my.kunal.file_explorer.Fragments.BrowseFragment
import my.kunal.file_explorer.Fragments.CleanFragment
import my.kunal.file_explorer.Fragments.ShareFragment
import my.kunal.file_explorer.search_operation.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    SideActivityOperation {

    private lateinit var selectedFragment: Fragment
    private var boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onClickOperations()
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


    private fun onClickOperations() {

        ivViewStyle.setOnClickListener {
            boolean = if (boolean) {
                it.setBackgroundResource(R.drawable.ic_view_grid)
                false
            } else {
                it.setBackgroundResource(R.drawable.ic_view_list)
                true
            }
        }

        ivMenu.setOnClickListener(View.OnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        })
        ivBack.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
        ivSearch.setOnClickListener(View.OnClickListener {
            val searchIntent = Intent(applicationContext, SearchActivity::class.java)
            startActivity(searchIntent)
        })
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

    override fun onSideActivitySelected(sideActivityName: String) {
        when (sideActivityName) {
            "Images" -> tvSideActivityTitle.text = "Images"
            "Videos" -> tvSideActivityTitle.text = "Videos"
            "Audio" -> tvSideActivityTitle.text = "Audio"
            "Documents" -> tvSideActivityTitle.text = "Documents"
            "Apps" -> tvSideActivityTitle.text = "Apps"
            "Downloads" -> tvSideActivityTitle.text = "Downloads"
            "Internal Storage" -> tvSideActivityTitle.text = "Internal Storage"
        }
    }

}