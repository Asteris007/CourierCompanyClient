package com.example.couriercompanyclient.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.couriercomanyclient.service.ApiService
import com.example.couriercompanyclient.adapter.ClientAdapter
import com.example.couriercompanyclient.model.Client
import com.example.couriercompanyclient.service.ApiClient
import com.example.couriercompclient.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomersActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var clientAdapter: ClientAdapter
    private lateinit var clientList: MutableList<Client>
    private lateinit var clientApi: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customers_view)

        recyclerView = findViewById(R.id.recyclerViewClientsView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        clientList = mutableListOf()
        clientAdapter = ClientAdapter(clientList)
        recyclerView.adapter = clientAdapter

        clientApi = ApiClient.apiService

        loadClients()
    }

    private fun loadClients() {
        clientApi.getAllClients().enqueue(object : Callback<List<Client>> {
            override fun onResponse(call: Call<List<Client>>, response: Response<List<Client>>) {
                if (response.isSuccessful) {
                    clientList.clear()
                    response.body()?.let { clients ->
                        clientList.addAll(clients)
                    }
                    clientAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@CustomersActivity, "Неуспешно зареждане на клиенти", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Client>>, t: Throwable) {
                Toast.makeText(this@CustomersActivity, "Грешка при зареждане на клиенти", Toast.LENGTH_SHORT).show()
                Log.e("CustomersActivity", "onFailure: ", t)
            }
        })
    }
}
