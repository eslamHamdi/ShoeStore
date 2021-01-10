package com.example.shoestore.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentLoginBinding
import kotlin.system.exitProcess

class LoginFragment : Fragment() {

    lateinit var binding:FragmentLoginBinding


    override fun onCreate(savedInstanceState: Bundle?)
    {
        //handle back button in the home fragment to assure app exit
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                exitApp()
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       binding  = DataBindingUtil
                .inflate(inflater,R.layout.fragment_login,container,false)

        binding.login.setOnClickListener {
            goToWelcome()
        }

binding.newUser.setOnClickListener {

    goToWelcome()
}

        return binding.root
    }

fun goToWelcome()
{
    val email = binding.emailEnter.text.toString()

    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(email))

}
// cover when going back by using the list fragment overflow menu
    fun exitApp()
    {

        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        startActivity(intent)
        exitProcess(0)

    }

}