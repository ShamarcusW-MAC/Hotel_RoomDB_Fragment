package com.example.week4_day1_hotel_roomdb.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.week4_day1_hotel_roomdb.R
import com.example.week4_day1_hotel_roomdb.database.GuestDatabase
import com.example.week4_day1_hotel_roomdb.database.GuestEntity
import kotlinx.android.synthetic.main.guest_add_layout.*

class GuestFragment: Fragment() {

    interface FragmentListener{
        fun updateResults()
    }

    private lateinit var gifImageView: ImageView


    fun setListener(fragmentListener: FragmentListener){
        this.fragmentListener = fragmentListener
    }

    lateinit var fragmentListener: FragmentListener
    lateinit var database: GuestDatabase


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.guest_add_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { fragmentActivity ->
            database = Room.databaseBuilder(
                fragmentActivity.applicationContext,
                GuestDatabase::class.java,
                "Guests.db"
            )
                .allowMainThreadQueries()
                .build()
        }

        //https://media.giphy.com/media/9nL6XPMuR9nIQ/giphy.gif
        gifImageView = view.findViewById(R.id.fragment_imageView)

        Glide.with(this).asGif()
            .load("https://media.giphy.com/media/9nL6XPMuR9nIQ/giphy.gif")
            .into(gifImageView)

        guest_insert_button.setOnClickListener {
            val name = name_insert_edittext.text.toString()
            val room = Integer.parseInt(room_insert_edittext.text.toString())
            val price = Integer.parseInt(price_insert_edittext.text.toString())
            val guestEntity = GuestEntity(name, room, price)
            database.guestDAO().addNewGuest(guestEntity)
            clearFields()
            fragmentListener.updateResults()
            fragmentManager?.popBackStack()
        }
    }

    private fun clearFields(){
        name_insert_edittext.text.clear()
        room_insert_edittext.text.clear()
        price_insert_edittext.text.clear()
    }


}