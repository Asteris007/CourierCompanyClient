package com.example.couriercompanyclient.model

data class Package(
    val packageId: Int,
    val clientId: Int,
    val courierId: Int,
    val registrationDate: String,
    val deliveryDate: String,
    val packageTypeId: Int,
    val statusId: Int,
    val priceId: Int,
    val cashOnDelivery: Boolean
)
