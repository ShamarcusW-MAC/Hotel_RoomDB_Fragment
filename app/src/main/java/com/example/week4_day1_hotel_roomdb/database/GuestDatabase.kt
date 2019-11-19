package com.example.week4_day1_hotel_roomdb.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [GuestEntity::class])
abstract class GuestDatabase : RoomDatabase() {
    abstract fun guestDAO(): GuestDAO
}