package com.urban.androidhomework.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationDetails(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("type") val type: String,
        @SerializedName("dimension") val dimension: String
) : Parcelable {
    override fun toString(): String {
        return "LocationDetails: id=$id, name='$name', type=$type, dimension='$dimension'"
    }
}