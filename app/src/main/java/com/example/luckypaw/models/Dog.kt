package com.example.luckypaw.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dog(
    var id: String? = "",
    var age: String? = "",
    var availability: String? = "",
    var breed: String? = "",
    var breeder: String? = "",
    var color: String? = "",
    var gender: String? = "",
    var height: String? = "",
    var image: String? = "",
    var imageURL: String? = "",
    var latitude: String? = "",
    var longitude: String? = "",
    var name: String? = "",
    var visit: String? = "",
    var weight: String? = "",
    var behavior: List<String>? = emptyList()
) : Parcelable
