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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LocationDetails

        if (id != other.id) return false
        if (name != other.name) return false
        if (type != other.type) return false
        if (dimension != other.dimension) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + dimension.hashCode()
        return result
    }
}