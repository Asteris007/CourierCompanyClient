package com.example.courierservice.model

data class User(
    val userId: Int = 0,
    val username: String,
    val password: String,
    val roleId: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String
)
