package com.example.shoestore.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

val args:WelcomeFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding:FragmentWelcomeBinding = DataBindingUtil
                .inflate(inflater,R.layout.fragment_welcome,container,false)
        val email = args.user

        Toast.makeText(this.requireContext(),"Welcome $email",Toast.LENGTH_LONG).show()

        binding.instruct.setOnClickListener {

            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment())
        }
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.over_flow_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return NavigationUI.onNavDestinationSelected(item,this.findNavController())||super.onOptionsItemSelected(item)


    }


}