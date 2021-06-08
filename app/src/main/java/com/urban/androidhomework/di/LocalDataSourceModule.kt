package com.urban.androidhomework.di

import com.urban.androidhomework.local.LocalDataRepository
import com.urban.androidhomework.local.LocalDataService
import com.urban.androidhomework.room.CartoonDAO
import com.urban.androidhomework.room.CartoonDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideCartoonDAO(cartoonDB: CartoonDB): CartoonDAO {
        return cartoonDB.getDAO()
    }

    @Provides
    @Singleton
    fun provideLocalRepository(cartoonDAO: CartoonDAO): LocalDataService = LocalDataRepository(cartoonDAO)
}