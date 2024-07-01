package com.example.couriercomanyclient.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.couriercomanyclient.service.ApiService
import com.example.couriercomanyclient.service.LoginRequest
import com.example.couriercomanyclient.service.LoginResponse
import com.example.couriercompclient.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var imageViewLogo: ImageView
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        imageViewLogo = findViewById(R.id.imageViewLogo)

        imageViewLogo.setImageResource(R.drawable.company_logo)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.74.80:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        buttonLogin.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            val loginRequest = LoginRequest(username, password)
            Log.d("LoginActivity", "Attempting login with username: $username and password: $password")

            apiService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        if (loginResponse != null && loginResponse.success) {
                            Log.d("LoginActivity", "Login successful")

                            val intent = Intent(this@LoginActivity, MainMenuActivity::class.java)
                            intent.putExtra("userRole", loginResponse.role)
                            startActivity(intent)
                        } else {
                            Log.d("LoginActivity", "Invalid credentials")
                            Toast.makeText(this@LoginActivity, "Грешно потребителско име или парола", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Log.d("LoginActivity", "Login request failed")
                        Toast.makeText(this@LoginActivity, "Грешка при вход", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d("LoginActivity", "Failed to connect to server: ${t.message}")
                    Toast.makeText(this@LoginActivity, "Грешка при свързване със сървъра", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
