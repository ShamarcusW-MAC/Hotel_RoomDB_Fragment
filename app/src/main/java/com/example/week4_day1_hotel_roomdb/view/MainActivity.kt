package com.example.week4_day1_hotel_roomdb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.week4_day1_hotel_roomdb.R
import com.example.week4_day1_hotel_roomdb.adapter.GuestRVAdapter
import com.example.week4_day1_hotel_roomdb.database.GuestDatabase
import com.example.week4_day1_hotel_roomdb.database.GuestEntity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GuestFragment.FragmentListener, GuestRVAdapter.GuestAdapterDelegate {

    lateinit var guestDB: GuestDatabase
    private lateinit var gifImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        gifImageView = findViewById(R.id.top_imageView)

        Glide.with(this).asGif()
            .load("https://media.tenor.com/images/c1c2218eaf2e23a4563f80baa0659b5e/tenor.gif")
            .into(gifImageView)

        guestDB = Room.databaseBuilder(
            this,
            GuestDatabase::class.java,
            "Guests.db"
        )
            .allowMainThreadQueries()
            .build()

        add_guest_button.setOnClickListener {
            val fragment = GuestFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_framelayout, fragment)
                .addToBackStack(fragment.tag)
                .commit()
        }
        setUpAdapter()

    }


    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if(fragment is GuestFragment)
            (fragment).setListener(this)
    }

    private fun setUpAdapter(){
        val adapter = GuestRVAdapter(guestDB.guestDAO().retrieveAllGuests(), this)
        guestlist_recycleview.adapter = adapter
        guestlist_recycleview.layoutManager = LinearLayoutManager(this)
    }

   override fun updateResults(){
        setUpAdapter()
    }
    override fun deleteGuest(guest: GuestEntity) {
    }
}
