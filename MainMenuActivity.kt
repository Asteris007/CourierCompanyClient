package com.example.couriercomanyclient.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.couriercomanyclient.ShowCompanies
import com.example.couriercompanyclient.activity.AddUserActivity
import com.example.couriercompanyclient.activity.CustomersActivity
import com.example.couriercompanyclient.activity.OrdersActivity
import com.example.couriercompclient.R

class MainMenuActivity : AppCompatActivity() {

    private lateinit var buttonOrders: Button
    private lateinit var buttonCustomers: Button
    private lateinit var buttonCompanies: Button
    private lateinit var buttonAddCompany: Button
    private lateinit var buttonAddCourierToCompany: Button
    private lateinit var buttonAddClient: Button
    private lateinit var buttonLogout: Button

    private var companyId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        buttonOrders = findViewById(R.id.buttonOrders)
        buttonCustomers = findViewById(R.id.buttonCustomers)
        buttonCompanies = findViewById(R.id.buttonCompanies)
        buttonAddCompany = findViewById(R.id.buttonAddCompany)
        buttonAddCourierToCompany = findViewById(R.id.buttonAddCourierToCompany)
        buttonAddClient = findViewById(R.id.buttonAddClient)
        buttonLogout = findViewById(R.id.buttonLogout)

        val userRole = intent.getStringExtra("userRole")
        companyId = intent.getIntExtra("COMPANY_ID", -1)
        when (userRole) {
            "1" -> {
                buttonAddCompany.isEnabled = true
                buttonAddCourierToCompany.isEnabled = true
                buttonAddClient.isEnabled = false
            }

            "2" -> {
                buttonAddCompany.isEnabled = false
                buttonAddCourierToCompany.isEnabled = false
                buttonAddClient.isEnabled = true
            }

            else -> {
                buttonAddCompany.isEnabled = false
                buttonAddCourierToCompany.isEnabled = false
                buttonAddClient.isEnabled = false
            }
        }


        buttonAddClient.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }

        buttonAddCompany.setOnClickListener {
            val intent = Intent(this, AddCompanyActivity::class.java)
            startActivity(intent)
        }

        buttonOrders.setOnClickListener {
            val intent = Intent(this, OrdersActivity::class.java)
            startActivity(intent)
        }

        buttonCustomers.setOnClickListener {
            val intent = Intent(this, CustomersActivity::class.java)
            startActivity(intent)
        }

        buttonCompanies.setOnClickListener {
            val intent = Intent(this, ShowCompanies::class.java)
            startActivity(intent)
        }


        buttonLogout.setOnClickListener {
            finish()
        }

        buttonAddCourierToCompany.setOnClickListener {
            val intent = Intent(this, AddCourierActivity::class.java)
            startActivity(intent)
        }
    }
}