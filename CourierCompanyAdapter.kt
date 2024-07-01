package com.example.couriercomanyclient.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.couriercomanyclient.service.CompanyResponse
import com.example.couriercompclient.R

class CourierCompanyAdapter : RecyclerView.Adapter<CourierCompanyAdapter.ViewHolder>() {

    private var courierCompanies: List<CompanyResponse> = listOf()

    fun setCourierCompanies(courierCompanies: List<CompanyResponse>) {
        this.courierCompanies = courierCompanies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_courier_company, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val courierCompany = courierCompanies[position]
        holder.companyNameTextView.text = courierCompany.name
        holder.cityIdTextView.text = courierCompany.cityId.toString()
        holder.streetIdTextView.text = courierCompany.streetId.toString()
        holder.numberTextView.text = courierCompany.number.toString()
    }

    override fun getItemCount(): Int = courierCompanies.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val companyNameTextView: TextView = view.findViewById(R.id.companyNameTextView)
        val cityIdTextView: TextView = view.findViewById(R.id.editTextCityId)
        val streetIdTextView: TextView = view.findViewById(R.id.editTextStreetId)
        val numberTextView: TextView = view.findViewById(R.id.editTextNumber)
    }
}
