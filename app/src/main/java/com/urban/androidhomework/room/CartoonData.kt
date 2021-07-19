package com.urban.androidhomework.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.urban.androidhomework.utils.CARTOON_TABLE

@Entity(tableName = CARTOON_TABLE)
data class CartoonData(
        @PrimaryKey
        val id: Int,
        val name: String,
        val status: String,
        val species: String,
        val type: String,
        val gender: String,
        val image: String,
        val date: Long,
        @TypeConverters(CartoonConverter::class)
        val location: CartoonLocation
) {
    override fun toString(): String {
        return "CartoonData:id=$id, name='$name', status='$status', species='$species', type=$type, gender='$gender', image='$image', date='$date', location=$location"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CartoonData

        if (id != other.id) return false
        if (name != other.name) return false
        if (status != other.status) return false
        if (species != other.species) return false
        if (type != other.type) return false
        if (gender != other.gender) return false
        if (image != other.image) return false
        if (date != other.date) return false
        if (location != other.location) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + species.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + gender.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + location.hashCode()
        return result
    }
}