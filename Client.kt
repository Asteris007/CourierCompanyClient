package com.example.couriercompanyclient.model

data class Client(
    val clientId: Int,
    val userId: Int,
    val cityId: Int,
    val streetId: Int,
    val recordedByCourier: Int,
    val numberStreet: Int,

)
