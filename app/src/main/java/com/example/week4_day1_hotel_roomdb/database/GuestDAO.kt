package com.example.week4_day1_hotel_roomdb.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GuestDAO {


    @Insert
    fun addNewGuest(guestEntity: GuestEntity)

    @Query("SELECT * FROM Guests" )
    fun retrieveAllGuests(): List<GuestEntity>

    @Delete
    fun deleteFromCars(guestEntity: GuestEntity)
}