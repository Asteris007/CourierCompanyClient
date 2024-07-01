package com.example.couriercomanyclient.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.couriercomanyclient.adapter.CourierAdapterCompanyView
import com.example.couriercomanyclient.model.Courier
import com.example.couriercomanyclient.model.CourierCompanyInfo
import com.example.couriercomanyclient.service.CourierRequest
import com.example.couriercompanyclient.service.ApiClient
import com.example.couriercompclient.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCourierActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CourierAdapterCompanyView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_courier)

        val userIdEditText = findViewById<EditText>(R.id.editTextUserId)
        val companyIdEditText = findViewById<EditText>(R.id.editTextCompanyId)
        val addButton = findViewById<Button>(R.id.buttonAddCourier)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CourierAdapterCompanyView()
        recyclerView.adapter = adapter

        addButton.setOnClickListener {
            val userId = userIdEditText.text.toString().toIntOrNull()
            val companyId = companyIdEditText.text.toString().toIntOrNull()

            if (userId != null && companyId != null) {
                val courierRequest = CourierRequest(userId, companyId)
                ApiClient.apiService.addCourier(courierRequest)
                    .enqueue(object : Callback<Courier> {
                        override fun onResponse(call: Call<Courier>, response: Response<Courier>) {
                            if (response.isSuccessful) {
                                Toast.makeText(this@AddCourierActivity, "Куриерът е добавен успешно", Toast.LENGTH_SHORT).show()
                                fetchCourierCompanies()  // Update the list after adding a courier
                            } else {
                                Toast.makeText(this@AddCourierActivity, "Неуспешно добавяне на куриер", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<Courier>, t: Throwable) {
                            Toast.makeText(this@AddCourierActivity, "Грешка: ${t.message}", Toast.LENGTH_SHORT).show()
                        }
                    })
            } else {
                Toast.makeText(this@AddCourierActivity, "Моля, въведете валидни идентификационни номера", Toast.LENGTH_SHORT).show()
            }
        }

        fetchCourierCompanies()  // Initial fetch to populate the list
    }

    private fun fetchCourierCompanies() {
        ApiClient.apiService.getCourierCompanyInfo()
            .enqueue(object : Callback<List<CourierCompanyInfo>> {
                override fun onResponse(call: Call<List<CourierCompanyInfo>>, response: Response<List<CourierCompanyInfo>>) {
                    if (response.isSuccessful) {
                        val companies = response.body() ?: emptyList()
                        Log.d("AddCourierActivity", "Получени данни: $companies")
                        adapter.setCourierCompanies(companies)
                    } else {

                        val errorMessage = response.errorBody()?.string()
                        Log.e("AddCourierActivity", "Грешка при зареждане на данните: $errorMessage")
                        Toast.makeText(this@AddCourierActivity, "Грешка при зареждане на данните: $errorMessage", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<CourierCompanyInfo>>, t: Throwable) {

                    Log.e("AddCourierActivity", "Грешка: ${t.message}", t)
                    Toast.makeText(this@AddCourierActivity, "Грешка: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
