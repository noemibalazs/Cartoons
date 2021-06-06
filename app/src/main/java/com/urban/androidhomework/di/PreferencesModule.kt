package com.urban.androidhomework.di

import android.content.SharedPreferences
import com.urban.androidhomework.preferences.PreferencesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesModule {

    @Singleton
    @Provides
    fun providePreferenceRepository(sharedPreferences: SharedPreferences) = PreferencesRepository(sharedPreferences)
}