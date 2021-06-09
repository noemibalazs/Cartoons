package com.urban.androidhomework.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterData(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("status") val status: String,
        @SerializedName("species") val species: String,
        @SerializedName("type") val type: String?,
        @SerializedName("gender") val gender: String,
        @SerializedName("image") val image: String,
        @SerializedName("created") val created: String,
        @SerializedName("location") val location: CharacterLocation) : Parcelable {

    override fun toString(): String {
        return "CharacterData: id=$id, name='$name', status='$status', species='$species', type=$type, gender='$gender', image='$image', created='$created', location=$location"
    }
}

