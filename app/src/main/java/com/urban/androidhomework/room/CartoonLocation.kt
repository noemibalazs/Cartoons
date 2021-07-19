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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CartoonLocation

        if (name != other.name) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + url.hashCode()
        return result
    }

}