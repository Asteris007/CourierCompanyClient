package com.example.couriercomanyclient.model




data class CourierCompany(
    var companyId: Int? = null,
    var name: String,
    var cityId: Int,
    var streetId: Int,
    var number: Int,
    var createFromAdmin: Int
)
