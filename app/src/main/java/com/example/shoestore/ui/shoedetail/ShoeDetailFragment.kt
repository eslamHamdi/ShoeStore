package com.example.shoestore.ui.shoedetail

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeDetailBinding
import com.example.shoestore.model.Shoe


class ShoeDetailFragment : Fragment(), AdapterView.OnItemSelectedListener{

     var Name:String = "null"
    var Company:String = "null"
    var Size :Double = 0.0
    var Description:String= "null"
    var Image :String = "null"
    var shoe: Shoe? =null
    lateinit var viewModel:ShowDetailViewModel
    lateinit var factory: ViewModelFactory




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding :FragmentShoeDetailBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_detail,container,false)
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


        if (savedInstanceState != null)
        {
           binding.enterName.text = savedInstanceState.get("Name") as Editable?
             binding.enterCompany.text = savedInstanceState.get("company") as Editable?
        binding.enterSize.text = savedInstanceState.get("size") as Editable?
           binding.enterDescription.text = savedInstanceState.get("description") as Editable?

        }

        Name = binding.enterName.text.toString()
        Company = binding.enterCompany.text.toString()
        Size = binding.enterSize.text.toString().toDouble()
        Description =binding.enterDescription.text.toString()
       //Image = spinner.selectedItem.toString()




        binding.shoeSave.setOnClickListener {

            shoe?.apply {

                this.name = Name
                this.company = Company
                this.size = Size
                this.description = Description
                this.images.toMutableList().add(Image)

            }
            factory = ViewModelFactory(shoe)
            viewModel = ViewModelProvider(this, factory)
                    .get(ShowDetailViewModel::class.java)

            findNavController().navigate(ShoeDetailFragmentDirections
                    .actionShoeDetailFragmentToNewShoeListFragment(viewModel.getShoe()))
        }
        binding.cancel.setOnClickListener {

            findNavController().navigate(ShoeDetailFragmentDirections
                    .actionShoeDetailFragmentToNewShoeListFragment())
        }




        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle)
    {
        super.onSaveInstanceState(outState)
        outState.putString("Name",Name)
        outState.putString("company",Company)
        outState.putString("description",Description)
        outState.putString("image",Image)
        outState.putDouble("size",Size)

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
    {
        

    }

    override fun onNothingSelected(parent: AdapterView<*>?)
    {

    }


}