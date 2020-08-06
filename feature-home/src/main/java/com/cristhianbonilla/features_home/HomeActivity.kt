package com.cristhianbonilla.features_home

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.cristhianbonilla.feature_home.R
import com.cristhianbonilla.features_home.ui.home.HomeFragmentDirections
import com.cristhianbonilla.features_home.ui.home.HomeMagazineState
import com.cristhianbonilla.foundations.base.BaseActivity
import com.cristhianbonilla.foundations.base.BaseState
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : BaseActivity<HomeState>(R.layout.activity_home, R.navigation.mobile_navigation, R.id.nav_host_fragment) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onStateChanged(state: HomeState) {
        super.onStateChanged(state)

        when(state){
           is HomeMagazineState.NavigateToMagazineDetails ->{
               innerNavigate(HomeFragmentDirections.actionNavigationHomeToPreviewMagazineFragment(state.item))
            }
        }
    }
}

open class HomeState : BaseState
open class PreMagazineState : BaseState