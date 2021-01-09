package com.example.shoestore.ui.newshoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestore.model.Shoe
import timber.log.Timber


class ShowListViewModel:ViewModel() {

   private var shoeList : MutableList<Shoe?>?=null

    init
    {
        shoeList = mutableListOf()

    }


    private var _shoeListliveData = MutableLiveData<MutableList<Shoe?>?>()

    val shoeLiveData:LiveData<MutableList<Shoe?>?>
            get() = _shoeListliveData


    fun getShoeData(shoe:Shoe?)
    {
        shoeList?.add(shoe)
        _shoeListliveData.value = shoeList

        println(shoeList)
    }

    override fun onCleared()
    {
        super.onCleared()
        Timber.e("viewModel destroyed")
    }

}