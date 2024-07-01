package com.example.couriercomanyclient.service

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val role: String?
)
