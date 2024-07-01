package com.example.couriercomanyclient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.couriercomanyclient.model.CourierCompanyInfo
import com.example.couriercompclient.R

class CourierAdapterCompanyView : RecyclerView.Adapter<CourierAdapterCompanyView.ViewHolder>() {

    private var courierCompanies: List<CourierCompanyInfo> = listOf()

    fun setCourierCompanies(courierCompanies: List<CourierCompanyInfo>) {
        this.courierCompanies = courierCompanies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_courier_company, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val courierCompany = courierCompanies[position]
        holder.companyNameTextView.text = courierCompany.companyName
        holder.courierNameTextView.text = courierCompany.courierName
    }

    override fun getItemCount(): Int = courierCompanies.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val companyNameTextView: TextView = view.findViewById(R.id.companyNameTextView)
        val courierNameTextView: TextView = view.findViewById(R.id.courierNameTextView)
    }
}
