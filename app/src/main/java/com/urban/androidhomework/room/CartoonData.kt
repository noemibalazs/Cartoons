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
}