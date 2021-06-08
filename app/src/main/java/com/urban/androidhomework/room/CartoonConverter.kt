package com.urban.androidhomework.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartoonConverter {

    @TypeConverter
    fun cartoonLocation2String(cartoonLocation: CartoonLocation): String {
        return Gson().toJson(cartoonLocation)
    }

    @TypeConverter
    fun string2CartoonLocation(json: String): CartoonLocation {
        val type = object : TypeToken<CartoonLocation>() {}.type
        return Gson().fromJson(json, type)
    }
}