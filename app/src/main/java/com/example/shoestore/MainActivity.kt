package com.example.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.example.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var drawer:DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                //setContentView(R.layout.activity_main)

        val binding :ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        drawer = binding.drawer

        Timber.plant(Timber.DebugTree())
    }
}