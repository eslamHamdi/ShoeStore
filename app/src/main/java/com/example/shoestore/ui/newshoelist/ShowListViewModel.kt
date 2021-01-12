package com.example.shoestore.ui.newshoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestore.model.Shoe
import timber.log.Timber

//the shared view Model
class ShowListViewModel:ViewModel() {

   private var shoeList : MutableList<Shoe?>?=mutableListOf()
    var name = ""
    var company = ""
    var description = ""
    var size = "0.0"
    var image:MutableList<String> = mutableListOf()


    private var _shoeListliveData = MutableLiveData<MutableList<Shoe?>?>()

    //applying encapsulation
    val shoeLiveData:LiveData<MutableList<Shoe?>?>
            get() = _shoeListliveData


    fun getShoeData()
    {
        //override empty string scenario
        if (size=="")
        {
            size = "0.0"
        }
        val shoe: Shoe = Shoe(name,size.toDouble(),company,description,image)
        shoeList?.add(shoe)
        _shoeListliveData.value = shoeList

    }

    //just for debugging and observing viewModel lifeCycle
    override fun onCleared()
    {
        super.onCleared()
        Timber.e("viewModel destroyed")
    }

}