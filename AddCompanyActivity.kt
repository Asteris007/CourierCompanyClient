package com.example.couriercomanyclient.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.couriercomanyclient.service.*
import com.example.couriercompclient.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddCompanyActivity : AppCompatActivity() {

    private lateinit var editTextCompanyName: EditText
    private lateinit var editTextCityId: EditText
    private lateinit var editTextStreetId: EditText
    private lateinit var editTextNumber: EditText
    private lateinit var buttonAddCompany: Button
    private lateinit var buttonDeleteCompany: Button
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_company)

        editTextCompanyName = findViewById(R.id.editTextCompanyName)
        editTextCityId = findViewById(R.id.editTextCityId)
        editTextStreetId = findViewById(R.id.editTextStreetId)
        editTextNumber = findViewById(R.id.editTextNumber)
        buttonAddCompany = findViewById(R.id.buttonAddCompany)
        buttonDeleteCompany = findViewById(R.id.buttonDeleteCompany)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.74.80:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        buttonAddCompany.setOnClickListener {
            addCompany()
        }

        buttonDeleteCompany.setOnClickListener {
            deleteCompany()
        }
    }

    private fun addCompany() {
        val companyName = editTextCompanyName.text.toString()
        val cityId = editTextCityId.text.toString().toIntOrNull()
        val streetId = editTextStreetId.text.toString().toIntOrNull()
        val number = editTextNumber.text.toString().toIntOrNull()

        if (companyName.isNotEmpty() && cityId != null && streetId != null && number != null) {
            val companyRequest = CompanyRequest(companyName, cityId, streetId, number, true)
            apiService.addCompany(companyRequest).enqueue(object : Callback<CompanyResponse> {
                override fun onResponse(call: Call<CompanyResponse>, response: Response<CompanyResponse>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@AddCompanyActivity, "Компанията е добавена успешно", Toast.LENGTH_SHORT).show()
                        Log.d("AddCompanyActivity", "Успешно добавяне: ${response.body()}")
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Toast.makeText(this@AddCompanyActivity, "Грешка при добавяне на компанията", Toast.LENGTH_SHORT).show()
                        Log.e("AddCompanyActivity", "Грешка при добавяне: $errorBody")
                    }
                }

                override fun onFailure(call: Call<CompanyResponse>, t: Throwable) {
                    Toast.makeText(this@AddCompanyActivity, "Грешка при свързване със сървъра", Toast.LENGTH_SHORT).show()
                    Log.e("AddCompanyActivity", "Грешка при свързване: ${t.message}", t)
                }
            })
        } else {
            Toast.makeText(this, "Моля, въведете валидни данни", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteCompany() {
        val companyName = editTextCompanyName.text.toString()
        if (companyName.isNotEmpty()) {
            apiService.deleteCompanyByName(companyName).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@AddCompanyActivity, "Компанията е изтрита успешно", Toast.LENGTH_SHORT).show()
                        Log.d("AddCompanyActivity", "Успешно изтриване")
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Toast.makeText(this@AddCompanyActivity, "Грешка при изтриване на компанията", Toast.LENGTH_SHORT).show()
                        Log.e("AddCompanyActivity", "Грешка при изтриване: $errorBody")
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@AddCompanyActivity, "Грешка при свързване със сървъра", Toast.LENGTH_SHORT).show()
                    Log.e("AddCompanyActivity", "Грешка при свързване: ${t.message}", t)
                }
            })
        } else {
            Toast.makeText(this, "Моля, въведете име на компания", Toast.LENGTH_SHORT).show()
        }
    }
}
