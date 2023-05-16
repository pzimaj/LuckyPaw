package com.example.luckypaw.models

data class User(
    var email: String? = "",
    var name: String? = "",
    var surname: String? = "",
    var dogs: List<String>? = emptyList()
)
