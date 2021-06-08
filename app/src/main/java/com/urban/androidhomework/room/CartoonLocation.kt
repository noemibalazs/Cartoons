package com.urban.androidhomework.room

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CartoonLocation(
        val name: String,
        val url: String
) : Parcelable {
    override fun toString(): String {
        return "CartoonLocation: name='$name', url=$url"
    }
}