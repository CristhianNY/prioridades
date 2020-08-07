package com.cristhianbonilla.features_home

import android.os.Bundle
import android.os.PersistableBundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.cristhianbonilla.feature_home.R
import com.cristhianbonilla.features_home.ui.home.HomeFragmentDirections
import com.cristhianbonilla.features_home.ui.home.HomeMagazineState
import com.cristhianbonilla.foundations.base.BaseActivity
import com.cristhianbonilla.foundations.base.BaseState
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity<HomeState>(R.layout.activity_home, R.navigation.mobile_navigation, R.id.nav_host_fragment) {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         navController = findNavController(R.id.nav_host_fragment)

        val topLevelDestination = setOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications
        )
        appBarConfiguration = AppBarConfiguration(topLevelDestination, parentDrawer)
        setupBottomNavigation()
    }

    override fun onStateChanged(state: HomeState) {
        super.onStateChanged(state)

        when(state){
           is HomeMagazineState.NavigateToMagazineDetails ->{
               innerNavigate(HomeFragmentDirections.actionNavigationHomeToPreviewMagazineFragment(state.item))
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    private fun setupBottomNavigation() {
        nav_view.apply {
            setupWithNavController(navController)
            setOnNavigationItemSelectedListener { menuItem ->
                bottomNavigationItemSelected(menuItem.itemId)
                true
            }
        }
    }

    private fun bottomNavigationItemSelected(itemId: Int) {
        navController.navigate(
            when (itemId) {
                R.id.navigation_home -> R.id.navigation_home
                R.id.navigation_dashboard -> R.id.navigation_dashboard
                R.id.navigation_notifications -> R.id.navigation_notifications
                else -> R.id.navigation_home
            }
        )
    }
}

open class HomeState : BaseState
open class PreMagazineState : BaseState