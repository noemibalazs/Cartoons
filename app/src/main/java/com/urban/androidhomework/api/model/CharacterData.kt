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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CharacterData

        if (id != other.id) return false
        if (name != other.name) return false
        if (status != other.status) return false
        if (species != other.species) return false
        if (type != other.type) return false
        if (gender != other.gender) return false
        if (image != other.image) return false
        if (created != other.created) return false
        if (location != other.location) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + species.hashCode()
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + gender.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + created.hashCode()
        result = 31 * result + location.hashCode()
        return result
    }
}

