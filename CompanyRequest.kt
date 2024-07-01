package com.example.couriercomanyclient.service

data class CompanyRequest(
    val name: String,
    val cityId: Int,
    val streetId: Int,
    val number: Int,
    val createFromAdmin: Boolean
)