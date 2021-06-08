package com.urban.androidhomework.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CartoonData::class], version = 1, exportSchema = false)
@TypeConverters(CartoonConverter::class)
abstract class CartoonDB : RoomDatabase() {

    abstract fun getDAO(): CartoonDAO
}