package com.example.couriercomanyclient.service

data class CompanyResponse(
    val companyId: Int?,
    val name: String,
    val cityId: Int,
    val streetId: Int,
    val number: Int,
    val createFromAdmin: Boolean
)