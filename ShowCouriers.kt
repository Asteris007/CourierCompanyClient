package com.example.couriercomanyclient.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.couriercomanyclient.CourierAdapter
import com.example.couriercomanyclient.model.Courier
import com.example.couriercompanyclient.service.ApiClient
import com.example.couriercompclient.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowCouriers : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CourierAdapter
    private var courierList = mutableListOf<Courier>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_couriers)

        val companyId = intent.getIntExtra("COMPANY_ID", -1)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CourierAdapter(courierList)
        recyclerView.adapter = adapter

        if (companyId != -1) {
            loadCouriers(companyId)
        } else {
            Toast.makeText(this, "Invalid company ID", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadCouriers(companyId: Int) {
        val call = ApiClient.apiService.getCouriersByCompanyId(companyId)
        call.enqueue(object : Callback<List<Courier>> {
            override fun onResponse(call: Call<List<Courier>>, response: Response<List<Courier>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        courierList.addAll(it)
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(this@ShowCouriers, "Error loading data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Courier>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@ShowCouriers, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
