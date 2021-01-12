package com.example.shoestore.ui.shoedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeDetailBinding
import com.example.shoestore.ui.newshoelist.ShowListViewModel


class ShoeDetailFragment : Fragment(), AdapterView.OnItemSelectedListener{

    var Image: MutableList<String> = mutableListOf()
    val viewModel: ShowListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        // Inflate the layout for this fragment

        val binding :FragmentShoeDetailBinding = DataBindingUtil
                .inflate(inflater,R.layout.fragment_shoe_detail,container,false)

        binding.viewModel = viewModel



        val spinner:Spinner =binding.spinner
        ArrayAdapter.createFromResource(
                this.requireContext(),
                R.array.shoes_array,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this



        binding.shoeSave.setOnClickListener {

              if (Image.isNotEmpty()){
                  viewModel.getShoeData()
                  restorefields()

                  findNavController().navigate(ShoeDetailFragmentDirections
                          .actionShoeDetailFragmentToNewShoeListFragment())
              }
            else
              {
                  Toast.makeText(this.requireContext(),"Please Choose Proper Image",Toast.LENGTH_SHORT).show()
              }


        }

        binding.cancel.setOnClickListener {

            findNavController().navigate(ShoeDetailFragmentDirections
                    .actionShoeDetailFragmentToNewShoeListFragment())
       }


        return binding.root
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
    {
        //avoid choosing title as image source
           if(parent?.getItemAtPosition(position).toString() == getString(R.string.flag))
           {
               Toast.makeText(this.requireContext(),"Don't Forget to Choose Image",Toast.LENGTH_SHORT).show()

           }
        else
           {
               Image.add(parent?.getItemAtPosition(position).toString())
               viewModel.image = Image
           }


    }

    override fun onNothingSelected(parent: AdapterView<*>?)
    {
        Toast.makeText(this.requireContext(),"Don't Forget to Choose Image",Toast.LENGTH_SHORT).show()
    }

    // restoring fields to default after saving an item to the list
    fun restorefields()
    {
        viewModel.name =""
        viewModel.company =""
        viewModel.description =""
        viewModel.size ="0.0"

    }






}