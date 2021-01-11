package com.example.shoestore.ui.newshoelist

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentNewShoeListBinding
import com.example.shoestore.model.Shoe


class NewShoeListFragment : Fragment()
{

    val args: NewShoeListFragmentArgs by navArgs()

    lateinit var name: TextView
    lateinit var size: TextView
    lateinit var company: TextView
    lateinit var descripe: TextView
    lateinit var image: ImageView
    //lateinit var viewItem: View
    var activity: Activity? = null

    //Activity model view model
    val viewModel: ShowListViewModel by activityViewModels()
    lateinit var linearLayout: LinearLayout


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        val binding: FragmentNewShoeListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_shoe_list, container, false)

        linearLayout = binding.linearlayout
        activity = this.requireActivity()


        val shoe = args.shoeOpject

//checking if the shoe is null to avoid adding null objects to the list
        if (shoe != null)
        {


            viewModel.getShoeData(shoe)

            viewModel.shoeLiveData.observe(this.requireActivity(), {

                addingItems(it)
            })


        } else
        {
            // if shoe is null do not add it and show the last saved list
            viewModel.shoeLiveData.observe(this.requireActivity(), {

                if (it != null && it.isNotEmpty())
                {
                    addingItems(it)


                }
            })
            //showing toast asking the user to add more items to the list
            Toast.makeText(this.requireContext(), "Please Add More Items", Toast.LENGTH_SHORT).show()
        }



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
        this.childFragmentManager.popBackStack(R.id.loginFragment, POP_BACK_STACK_INCLUSIVE)
        return NavigationUI.onNavDestinationSelected(item, this.findNavController()) || super.onOptionsItemSelected(item)


    }

    fun addingItems(list: MutableList<Shoe?>?)
    {
       //linearLayout.removeAllViews()
        for (item in list!!)
        {
            if (item != null)
            {

                if(activity != null && isAdded)
                {
                    val view = itemInflator()
                    name.text = getString(R.string.shoesname,item.name)
                    size.text = getString(R.string.shoessize,item.size.toString())
                    company.text = getString(R.string.shoescompany,item.company)
                    descripe.text = getString(R.string.description,item.description)
                    val uri = "@drawable/${item.images.first()}"
                    val imageResource = this.activity?.resources?.getIdentifier(uri, null, this.requireContext().packageName)
                    image.setImageResource(imageResource!!)
                    linearLayout.addView(view)
                }

            }

        }

    }
    fun itemInflator():View
    {
      var viewItem = View.inflate(this.context, R.layout.show_item, null)
            name = viewItem.findViewById(R.id.name)
            size = viewItem.findViewById(R.id.size)
            company = viewItem.findViewById(R.id.company)
            descripe = viewItem.findViewById(R.id.description)
            image = viewItem.findViewById(R.id.image)

        return viewItem

    }



}





