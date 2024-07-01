package com.example.couriercomanyclient.service

import com.example.couriercomanyclient.model.Courier
import com.example.couriercomanyclient.model.CourierCompanyInfo
import com.example.couriercompanyclient.model.Client
import com.example.courierservice.model.User
import com.example.couriercompanyclient.model.Package
import retrofit2.Call
import retrofit2.http.*



data class LoginRequest(val username: String, val password: String)

interface ApiService {
    @GET("/api/courier-companies")
    fun getAllCourierCompaniesInfo():Call<List<CourierCompanyInfo>>

    @GET("/api/clients")
    fun getAllClients(): Call<List<Client>>

    @GET("/api/packages")
    fun getAllPackages(): Call<List<Package>>

    @GET("/api/users")
    fun getAllUsers(): Call<List<User>>

    @POST("/api/users")
    fun createUser(@Body user: User): Call<User>

    @GET("/api/courier-companies")
    fun getAllCourierCompanies(): Call<List<CompanyResponse>>

    @POST("/api/users/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("/api/courier-companies")
    fun addCompany(@Body companyRequest: CompanyRequest): Call<CompanyResponse>

    @DELETE("/api/courier-companies/name/{name}")
    fun deleteCompanyByName(@Path("name") name: String): Call<Void>

    @GET("/api/couriers/company/{companyId}")
    fun getCouriersByCompanyId(@Path("companyId") companyId: Int): Call<List<Courier>>

    @GET("/api/couriers/courier-companies-with-users")
    fun getCourierCompanyInfo(): Call<List<CourierCompanyInfo>>

    @POST("api/couriers")
    fun addCourier(@Body courierRequest: CourierRequest): Call<Courier>
}
