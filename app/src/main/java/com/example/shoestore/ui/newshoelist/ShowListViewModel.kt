package com.example.shoestore.ui.newshoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestore.model.Shoe


class ShowListViewModel:ViewModel() {

   var shoeList : ArrayList<Shoe>?=null

    init
    {
        shoeList = ArrayList()

    }


    private var _shoeListliveData = MutableLiveData<ArrayList<Shoe>?>()

    val shoeLiveData:LiveData<ArrayList<Shoe>?>
            get() = _shoeListliveData


    fun getShoeData(shoe:Shoe)
    {
        shoeList?.add(shoe)
        _shoeListliveData.value = shoeList
    }


}