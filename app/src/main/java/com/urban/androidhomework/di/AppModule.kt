package com.urban.androidhomework.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.urban.androidhomework.room.CartoonDB
import com.urban.androidhomework.utils.CARTOON_DB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    @ForApplication
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideSharedPreference(): SharedPreferences {
        return application.getSharedPreferences(application.packageName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideCartoonDB(): CartoonDB {
        return Room.databaseBuilder(application.applicationContext, CartoonDB::class.java, CARTOON_DB).build()
    }
}