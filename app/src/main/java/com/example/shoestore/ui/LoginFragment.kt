package com.example.shoestore.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var binding:FragmentLoginBinding
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

}