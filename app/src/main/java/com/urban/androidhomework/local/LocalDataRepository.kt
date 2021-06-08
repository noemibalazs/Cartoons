package com.urban.androidhomework.local

import com.urban.androidhomework.room.CartoonDAO
import com.urban.androidhomework.room.CartoonData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataRepository @Inject constructor(private val dao: CartoonDAO) : LocalDataService {

    override fun addCartoonList(cartoons: MutableList<CartoonData>) {
        return dao.addCartoonList(cartoons)
    }

    override fun filteredCartoons(filterCondition: Long): MutableList<CartoonData> {
        return dao.filteredCartoons(filterCondition)
    }

    override fun getCartoons(): MutableList<CartoonData> {
        return dao.getCartoons()
    }
}