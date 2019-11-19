package com.example.week4_day1_hotel_roomdb.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Guests")
class GuestEntity(
    @PrimaryKey(autoGenerate = true) var guestID: Int?,
    @ColumnInfo(name = "guestName") val guestName: String,
    @ColumnInfo(name = "guestRoom") val guestRoom: Int,
    @ColumnInfo(name = "guestPrice") val guestPrice: Int
    ) {
        constructor(guestName: String, guestRoom: Int, guestPrice: Int) : this(null, guestName, guestRoom, guestPrice)
}