package com.example.shoestore.ui.newshoelist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentNewShoeListBinding
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber


class NewShoeListFragment : Fragment() {

    val args :NewShoeListFragmentArgs by navArgs()
    var adapter = ShoeListAdapter(null)
    //Activity model view model
    val viewModel: ShowListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentNewShoeListBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_new_shoe_list,container,false)
       //using a recycler for dynamic item adding instead of listview or addview
        val recycler = binding.recycler
        recycler.adapter = adapter
        val shoe = args.shoeOpject
//checking if the shoe is null to avoid adding null objects to the list
if (shoe != null)
{
    Timber.e(println(shoe).toString())

    viewModel.getShoeData(shoe)

    viewModel.shoeLiveData.observe(this.requireActivity(),{

        adapter.getShoeList(it)
    })


}else
{
    // if shoe is null do not add it and show the last saved list
    viewModel.shoeLiveData.observe(this.requireActivity(),{

        adapter.getShoeList(it)
    })
    //showing toast asking the user to add more items to the list
   Toast.makeText(this.requireContext(),"Please Add More Items",Toast.LENGTH_SHORT).show()
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
        inflater.inflate(R.menu.over_flow_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return NavigationUI.onNavDestinationSelected(item,this.findNavController())||super.onOptionsItemSelected(item)


    }


}