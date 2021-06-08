package com.urban.androidhomework.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartoonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCartoonList(cartoons: MutableList<CartoonData>)

    @Query("SELECT * FROM cartoon_table WHERE date >= :filterCondition ORDER BY id")
    fun filteredCartoons(filterCondition: Long): MutableList<CartoonData>

    @Query("SELECT * FROM cartoon_table")
    fun getCartoons():  MutableList<CartoonData>
}