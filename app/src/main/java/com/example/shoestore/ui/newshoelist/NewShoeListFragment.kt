package com.example.shoestore.ui.newshoelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentNewShoeListBinding


class NewShoeListFragment : Fragment() {

    val args :NewShoeListFragmentArgs by navArgs()
    var adapter = ShoeListAdapter(null)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentNewShoeListBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_new_shoe_list,container,false)

        val recycler = binding.recycler
        recycler.adapter = adapter

        val viewModel:ShowListViewModel by viewModels()

        val shoe = args.shoeOpject

        viewModel.getShoeData(shoe)

        viewModel.shoeLiveData.observe(this.requireActivity(),{

            adapter.getShoeList(it)
        })

        binding.floatButton.setOnClickListener {

            findNavController().navigate(NewShoeListFragmentDirections.actionNewShoeListFragmentToShoeDetailFragment())
        }


        return binding.root
    }


}