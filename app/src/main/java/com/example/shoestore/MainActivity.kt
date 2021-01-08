package com.example.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.shoestore.databinding.ActivityMainBinding
import com.example.shoestore.ui.WelcomeFragmentDirections
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var drawer:DrawerLayout
    lateinit var toolbar :Toolbar
    lateinit var appBarconfig : AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                //setContentView(R.layout.activity_main)

        val binding :ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        drawer = binding.drawer
        toolbar = binding.toolbar

        setSupportActionBar(toolbar)

        val navController = this.findNavController(R.id.myNavHostFragment)

        setupActionBarWithNavController(navController,drawer)
        NavigationUI.setupWithNavController(binding.navView,navController)
        appBarconfig = AppBarConfiguration(navController.graph,drawer)
        checkingDrawerState(navController,drawer)


        Timber.plant(Timber.DebugTree())

    }
    fun checkingDrawerState(navController:NavController,drawerLayout:DrawerLayout)
    {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.welcomeFragment)
            {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

            }else
            {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean
    {
        val navController = findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController,drawer)
    }
}



