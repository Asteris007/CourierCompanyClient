package com.example.couriercompanyclient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.couriercompanyclient.model.Client
import com.example.couriercompclient.R

class ClientAdapter(private val clientList: List<Client>) : RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {

    class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clientId: TextView = itemView.findViewById(R.id.tvClientId)
        val userId: TextView = itemView.findViewById(R.id.tvUserId)
        val cityId: TextView = itemView.findViewById(R.id.tvCityId)
        val streetId: TextView = itemView.findViewById(R.id.tvStreetId)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.client_item, parent, false)
        return ClientViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client = clientList[position]
        holder.clientId.text = client.clientId.toString()
        holder.userId.text = client.userId.toString()
        holder.cityId.text = client.cityId.toString()
        holder.streetId.text = client.streetId.toString()

    }

    override fun getItemCount() = clientList.size
}
