package com.example.shoestore.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Shoe(var name: String ="", var size: Double= 0.0, var company: String="", var description: String="",
                var images: List<String> = mutableListOf()) : Parcelable
