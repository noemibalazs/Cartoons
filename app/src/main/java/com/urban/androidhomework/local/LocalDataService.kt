package com.urban.androidhomework.local

import com.urban.androidhomework.room.CartoonData

interface LocalDataService {

    fun addCartoonList(cartoons: MutableList<CartoonData>)

    fun filteredCartoons(filterCondition: Long): MutableList<CartoonData>

    fun getCartoons():  MutableList<CartoonData>
}