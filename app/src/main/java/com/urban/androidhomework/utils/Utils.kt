package com.urban.androidhomework.utils

import com.urban.androidhomework.api.model.Character
import retrofit2.Response

class Utils {
    companion object {
        fun getName(characterResponse: Response<Character>): List<String> {
            val names = mutableListOf<String>()
            for (ch in characterResponse.body()!!.results) {
                names.add(ch.name)
            }

            return names
        }
    }
}