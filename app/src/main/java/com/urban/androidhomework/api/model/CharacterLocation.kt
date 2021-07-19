package com.urban.androidhomework.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterLocation(
        @SerializedName("name") val name: String,
        @SerializedName("url") val url: String?
) : Parcelable {
    override fun toString(): String {
        return "CharacterLocation: url=$url, name='$name'"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CharacterLocation

        if (name != other.name) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (url?.hashCode() ?: 0)
        return result
    }
}