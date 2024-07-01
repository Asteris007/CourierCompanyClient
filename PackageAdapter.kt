package com.example.couriercompanyclient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.couriercompanyclient.model.Package
import com.example.couriercompclient.R

class PackageAdapter(private val packageList: List<Package>) : RecyclerView.Adapter<PackageAdapter.PackageViewHolder>() {

    class PackageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val packageId: TextView = itemView.findViewById(R.id.tvPackageId)
        val status: TextView = itemView.findViewById(R.id.tvStatus)
        val deliveryDate: TextView = itemView.findViewById(R.id.tvDeliveryDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.package_item, parent, false)
        return PackageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PackageViewHolder, position: Int) {
        val packageItem = packageList[position]
        holder.packageId.text = "Пратка ID: ${packageItem.packageId}"
        holder.status.text = "Статус: ${packageItem.statusId}"
        holder.deliveryDate.text = "Дата на доставка: ${packageItem.deliveryDate}"
    }

    override fun getItemCount() = packageList.size
}
