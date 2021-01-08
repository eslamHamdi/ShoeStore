package com.example.shoestore.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

import java.util.ArrayList

@Parcelize
data class Shoe(var name: String, var size: Double, var company: String, var description: String,
                var images: List<String> = mutableListOf()) : Parcelable
