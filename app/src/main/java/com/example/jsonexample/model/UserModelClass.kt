package com.example.jsonexample.model

data class User(
    val users: ArrayList<UserModelClass>
)

data class UserModelClass(
    val id: Int,
    val name: String,
    val email: String,
    val gender: String,
    val weight: Double,
    val height: Int,
    val phone: Phone
)

data class Phone(
    val mobile: String,
    val office: String
)