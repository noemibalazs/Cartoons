package com.urban.androidhomework.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesRepository @Inject constructor(private val sharedPreferences: SharedPreferences) {

    var index: Int
        set(value) {
            sharedPreferences.edit().putInt(KEY_INDEX, value).apply()
        }
        get() = sharedPreferences.getInt(KEY_INDEX, 0)

    companion object {
        const val KEY_INDEX = "key_index"
    }
}