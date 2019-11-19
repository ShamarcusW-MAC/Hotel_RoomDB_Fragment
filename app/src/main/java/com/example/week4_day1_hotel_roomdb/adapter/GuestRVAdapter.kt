package com.example.week4_day1_hotel_roomdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week4_day1_hotel_roomdb.R
import com.example.week4_day1_hotel_roomdb.database.GuestEntity

class GuestRVAdapter(private val currentGuests: List<GuestEntity>, private val guestAdapterDelegate: GuestAdapterDelegate ) :
    RecyclerView.Adapter<GuestRVAdapter.GuestViewHolder>(){

    interface GuestAdapterDelegate{
        fun deleteGuest(guest: GuestEntity)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GuestRVAdapter.GuestViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.guest_item_layout,
                parent,
                false
            )
        return GuestViewHolder(view)

    }

    override fun getItemCount(): Int {
        return currentGuests.size
    }

    override fun onBindViewHolder(holder: GuestRVAdapter.GuestViewHolder, position: Int) {
        holder.apply {
            guestName.text = "${currentGuests[position].guestName}"
            guestRoom.text = currentGuests[position].guestRoom.toString()
            guestPrice.text = currentGuests[position].guestPrice.toDollar()
        }
    }

    private fun Int.toDollar() : String {
        return "$$this.00"
    }


    inner class GuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val guestName : TextView = itemView.findViewById(R.id.guest_name_textview)
        val guestRoom : TextView = itemView.findViewById(R.id.guest_room_textview)
        val guestPrice : TextView = itemView.findViewById(R.id.guest_price_textview)

    }


}