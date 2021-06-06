package com.urban.androidhomework.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
        @SerializedName("results") val results: List<CharacterData>
) : Parcelable {
    override fun toString(): String {
        return "Character: results size - ${results.size}"
    }
}
