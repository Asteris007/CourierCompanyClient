package com.example.couriercompanyclient.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.couriercomanyclient.service.ApiService
import com.example.couriercompanyclient.adapter.UserAdapter
import com.example.couriercompanyclient.service.ApiClient
import com.example.couriercompclient.R
import com.example.courierservice.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddUserActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var userList: MutableList<User>
    private lateinit var userApi: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_client_activity)

        recyclerView = findViewById(R.id.recyclerViewUsers)
        recyclerView.layoutManager = LinearLayoutManager(this)
        userList = mutableListOf()
        userAdapter = UserAdapter(userList)
        recyclerView.adapter = userAdapter

        userApi = ApiClient.apiService

        loadUsers()

        findViewById<Button>(R.id.btnAddUser).setOnClickListener {
            addUser()
        }
    }

    private fun loadUsers() {
        userApi.getAllUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    userList.clear()
                    response.body()?.let { userList.addAll(it) }
                    userAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@AddUserActivity, "Неуспешно зареждане на потребители", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(this@AddUserActivity, "Грешка при зареждане на потребители", Toast.LENGTH_SHORT).show()
                Log.e("AddUserActivity", "onFailure: ", t)
            }
        })
    }

    private fun addUser() {
        val username = findViewById<EditText>(R.id.etUsername).text.toString()
        val password = findViewById<EditText>(R.id.etPassword).text.toString()
        val firstName = findViewById<EditText>(R.id.etFirstName).text.toString()
        val lastName = findViewById<EditText>(R.id.etLastName).text.toString()
        val email = findViewById<EditText>(R.id.etEmail).text.toString()
        val phone = findViewById<EditText>(R.id.etPhone).text.toString()
        val roleId = 2

        val user = User(
            username = username,
            password = password,
            roleId = roleId,
            firstName = firstName,
            lastName = lastName,
            email = email,
            phone = phone
        )

        userApi.createUser(user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@AddUserActivity, "Потребителят е добавен успешно", Toast.LENGTH_SHORT).show()
                    loadUsers() // Заредете потребителите след успешен запис
                } else {
                    Toast.makeText(this@AddUserActivity, "Неуспешно добавяне на потребител", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@AddUserActivity, "Грешка при добавяне на потребител", Toast.LENGTH_SHORT).show()
                Log.e("AddUserActivity", "onFailure: ", t)
            }
        })
    }
}
