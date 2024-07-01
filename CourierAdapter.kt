package com.example.couriercomanyclient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.couriercomanyclient.model.Courier
import com.example.couriercompclient.R

class CourierAdapter(private val courierList: List<Courier>) :
    RecyclerView.Adapter<CourierAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_courier, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val courier = courierList[position]
        holder.nameTextView.text = courier.userId.toString()
    }

    override fun getItemCount(): Int {
        return courierList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    }
}
