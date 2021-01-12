package com.example.shoestore.ui.newshoelist

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentNewShoeListBinding
import com.example.shoestore.databinding.ShowItemBinding
import com.example.shoestore.model.Shoe
import kotlin.system.exitProcess


class NewShoeListFragment : Fragment()
{

    //val args: NewShoeListFragmentArgs by navArgs()

   lateinit var viewItem:ShowItemBinding

    //Activity level view model
    val viewModel: ShowListViewModel by activityViewModels()

    lateinit var linearLayout: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        //ensuring exiting the app if the user pressed the back button
        this.activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                exitApp()
            }
        })
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        val binding: FragmentNewShoeListBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_new_shoe_list, container, false)

         //parent layout for shoes items
        linearLayout = binding.linearlayout


            // if shoe is null do not add it and show the last saved list
            viewModel.shoeLiveData.observe(this.requireActivity(), {

                 //this check to avoid error during navigating from instructions to the shoe list
                if (it != null && it.isNotEmpty())
                {
                    addingItems(it)


                }else{
                    //showing toast asking the user to add more items to the list
                    Toast.makeText(this.requireContext(), "Please Add More Items", Toast.LENGTH_SHORT).show()
                }
            })





        binding.floatButton.setOnClickListener {

            findNavController().navigate(NewShoeListFragmentDirections.actionNewShoeListFragmentToShoeDetailFragment())
        }
        //enable overflow menu
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.over_flow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return NavigationUI.onNavDestinationSelected(item, this.findNavController()) || super.onOptionsItemSelected(item)

    }

    fun addingItems(list: MutableList<Shoe?>?)
    {
        //linearLayout.removeAllViews()
      //looping at each item in the shoe list to add a view for each one under parent linear layout
        for (item in list!!)
        {
            if (item != null)
            {
                //avoiding illegalstate error by assuring that the fragment already attached to the activity
                if(activity != null && isAdded)
                {
                    val view = itemInflator()
                    viewItem.name.text = getString(R.string.shoesname,item.name)
                    viewItem.size.text = getString(R.string.shoessize,item.size.toString())
                    viewItem.company.text = getString(R.string.shoescompany,item.company)
                    viewItem.description.text = getString(R.string.description,item.description)
                    val uri = "@drawable/${item.images.first()}"
                    val imageResource = resources.getIdentifier(uri, null, this.requireContext().packageName)
                    viewItem.image.setImageResource(imageResource)
                    linearLayout.addView(view)
                }

            }

        }

    }

    //inflate the shoe item view then binding to its corresponding data binding class
    fun itemInflator():View
    {
      val view = View.inflate(this.context, R.layout.show_item, null)
         viewItem = DataBindingUtil.bind(view)!!
        return view
    }

//method for immediate app exit
    fun exitApp()
    {

        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        startActivity(intent)
        exitProcess(0)

    }



}





