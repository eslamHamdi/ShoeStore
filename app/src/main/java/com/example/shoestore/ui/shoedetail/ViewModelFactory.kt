package com.example.shoestore.ui.shoedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoestore.model.Shoe

class ViewModelFactory(private val shoe:Shoe?) : ViewModelProvider.Factory  {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(ShowDetailViewModel::class.java)) {
            return ShowDetailViewModel(shoe!!) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }


}