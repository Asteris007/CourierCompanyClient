package com.example.couriercompanyclient.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.couriercomanyclient.service.ApiService
import com.example.couriercompanyclient.adapter.PackageAdapter
import com.example.couriercompanyclient.model.Package
import com.example.couriercompanyclient.service.ApiClient
import com.example.couriercompclient.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersActivity : AppCompatActivity() {

    private lateinit var recyclerViewNotReceived: RecyclerView
    private lateinit var recyclerViewDelivered: RecyclerView
    private lateinit var notReceivedAdapter: PackageAdapter
    private lateinit var deliveredAdapter: PackageAdapter
    private lateinit var notReceivedList: MutableList<Package>
    private lateinit var deliveredList: MutableList<Package>
    private lateinit var packageApi: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        recyclerViewNotReceived = findViewById(R.id.recyclerViewNotReceived)
        recyclerViewNotReceived.layoutManager = LinearLayoutManager(this)
        notReceivedList = mutableListOf()
        notReceivedAdapter = PackageAdapter(notReceivedList)
        recyclerViewNotReceived.adapter = notReceivedAdapter

        recyclerViewDelivered = findViewById(R.id.recyclerViewDelivered)
        recyclerViewDelivered.layoutManager = LinearLayoutManager(this)
        deliveredList = mutableListOf()
        deliveredAdapter = PackageAdapter(deliveredList)
        recyclerViewDelivered.adapter = deliveredAdapter

        packageApi = ApiClient.apiService


        loadPackages()
    }

    private fun loadPackages() {
        packageApi.getAllPackages().enqueue(object : Callback<List<Package>> {
            override fun onResponse(call: Call<List<Package>>, response: Response<List<Package>>) {
                if (response.isSuccessful) {
                    val packages = response.body() ?: emptyList()
                    Log.d("OrdersActivity", "Received packages: $packages")
                    notReceivedList.clear()
                    deliveredList.clear()
                    for (packageItem in packages) {
                        if (packageItem.statusId == 3) {
                            notReceivedList.add(packageItem)
                        } else if (packageItem.statusId == 1) {
                            deliveredList.add(packageItem)
                        }
                    }
                    notReceivedAdapter.notifyDataSetChanged()
                    deliveredAdapter.notifyDataSetChanged()
                } else {
                    Log.e("OrdersActivity", "Response not successful: ${response.errorBody()}")
                    Toast.makeText(this@OrdersActivity, "Неуспешно зареждане на пратки", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Package>>, t: Throwable) {
                Log.e("OrdersActivity", "onFailure: ", t)
                Toast.makeText(this@OrdersActivity, "Грешка при зареждане на пратки", Toast.LENGTH_SHORT).show()
            }
        })
    }}


