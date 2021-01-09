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
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeDetailBinding
import com.example.shoestore.model.Shoe
import com.example.shoestore.ui.newshoelist.ShowListViewModel


class ShoeDetailFragment : Fragment(), AdapterView.OnItemSelectedListener{

     var Name:String = "null"
    var Company:String = "null"
    var Size :String = "0.0"
    var Description:String= "null"
    var Image: MutableList<String> = mutableListOf()
    var shoe: Shoe? =Shoe("",0.0,"","")
    var flag = false
    val viewModel: ShowListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        // Inflate the layout for this fragment

        val binding :FragmentShoeDetailBinding = DataBindingUtil
                .inflate(inflater,R.layout.fragment_shoe_detail,container,false)

        binding.detailFragment = this


        //restoring ui after any configuration changes
        if (savedInstanceState != null)
        {
           binding.enterName.text = savedInstanceState.get("Name") as Editable?
             binding.enterCompany.text = savedInstanceState.get("company") as Editable?
        binding.enterSize.text = savedInstanceState.get("size") as Editable?
           binding.enterDescription.text = savedInstanceState.get("description") as Editable?

        }

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
            //avoid choosing title as image source
            flag = Image.contains(getString(R.string.flag))
             //make sure the list is not empty
            if (!flag && Image.isNotEmpty())
            {
                shoe?.apply {

                    this.name = Name
                    this.company = Company
                    this.size = Size.toDouble()
                    this.description = Description
                    this.images = Image
                }


                findNavController().navigate(ShoeDetailFragmentDirections
                    .actionShoeDetailFragmentToNewShoeListFragment(shoe))
            }

            else
            {
                //empty the list to remove the flag word
                if (Image.isNotEmpty())
                {
                    Image.clear()
                }
                Toast.makeText(this.requireContext(),"Please Choose Proper Image",Toast.LENGTH_SHORT).show()
            }

        }
        binding.cancel.setOnClickListener {

            findNavController().navigate(ShoeDetailFragmentDirections
                    .actionShoeDetailFragmentToNewShoeListFragment(null))
        }


        return binding.root
    }
//configuration changes safety
    override fun onSaveInstanceState(outState: Bundle)
    {
        super.onSaveInstanceState(outState)
        outState.putString("Name",Name)
        outState.putString("company",Company)
        outState.putString("description",Description)
        outState.putString("size",Size)

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
    {

           val image = parent?.getItemAtPosition(position).toString()
           Image.add(image)

    }

    override fun onNothingSelected(parent: AdapterView<*>?)
    {
        Toast.makeText(this.requireContext(),"Don't Forget to Choose Image",Toast.LENGTH_SHORT).show()
    }



}