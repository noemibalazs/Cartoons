package com.urban.androidhomework.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterLocation(
        @SerializedName("name") val name: String,
        @SerializedName("url") val url: String? = null
) : Parcelable {
    override fun toString(): String {
        return "CharacterLocation: url=$url, name='$name'"
    }
}