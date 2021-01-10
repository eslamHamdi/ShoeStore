package com.example.shoestore.ui.main_activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.ActivityMainBinding
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    lateinit var drawer:DrawerLayout
    lateinit var toolbar :Toolbar
    lateinit var appBarconfig : AppBarConfiguration
    lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        val binding :ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        drawer = binding.drawer
        toolbar = binding.toolbar

        setSupportActionBar(toolbar)

         navController = this.findNavController(R.id.myNavHostFragment)

        setupActionBarWithNavController(navController, drawer)
        NavigationUI.setupWithNavController(binding.navView, navController)
        appBarconfig = AppBarConfiguration(navController.graph, drawer)
        checkingDrawerState(navController, drawer)




    }
    fun checkingDrawerState(navController: NavController, drawerLayout: DrawerLayout)
    {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == controller.graph.startDestination)
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

        return NavigationUI.navigateUp(navController, drawer)
    }
//hiding keyboard if edit texts out of focus
    override fun onUserInteraction()
    {
        super.onUserInteraction()
        if (currentFocus != null)
        {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }




}



