package com.example.couriercomanyclient

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.couriercomanyclient.adapter.CourierAdapterCompanyView
import com.example.couriercomanyclient.model.CourierCompanyInfo
import com.example.couriercompanyclient.service.ApiClient
import com.example.couriercompclient.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowCompanies : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CourierAdapterCompanyView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_companies)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CourierAdapterCompanyView()
        recyclerView.adapter = adapter

        loadCourierCompanies()
    }

    private fun loadCourierCompanies() {
        val call = ApiClient.apiService.getAllCourierCompaniesInfo()
        call.enqueue(object : Callback<List<CourierCompanyInfo>> {
            override fun onResponse(call: Call<List<CourierCompanyInfo>>, response: Response<List<CourierCompanyInfo>>) {
                if (response.isSuccessful) {
                    val companies = response.body() ?: emptyList()
                    Log.d("ShowCompanies", "Получени данни: $companies")
                    adapter.setCourierCompanies(companies)
                } else {
                    val errorMessage = response.errorBody()?.string()
                    Log.e("ShowCompanies", "Грешка при зареждане на данните: $errorMessage")
                    Toast.makeText(this@ShowCompanies, "Грешка при зареждане на данните: $errorMessage", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<CourierCompanyInfo>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@ShowCompanies, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
