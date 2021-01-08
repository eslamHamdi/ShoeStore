package com.example.shoestore.ui.shoedetail

import androidx.lifecycle.ViewModel
import com.example.shoestore.model.Shoe

class ShowDetailViewModel(shoe: Shoe):ViewModel() {

    private var shoe:Shoe? =null

    init
    {
       this.shoe = shoe
    }

    fun getShoe():Shoe?
    {
        return this.shoe
    }
}