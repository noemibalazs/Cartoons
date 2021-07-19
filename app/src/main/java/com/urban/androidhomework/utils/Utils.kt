package com.urban.androidhomework.utils

import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        private const val INPUT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        const val OUTPUT_FORMAT = "dd-MM-yyyy"

        fun longForOutPut(created: String): Long {
            val oldFormat = SimpleDateFormat(OUTPUT_FORMAT, Locale.getDefault())
            val date = oldFormat.parse(created)
            return date.time
        }

        fun long2String(date: Long): String {
            val oldFormat = SimpleDateFormat(INPUT_FORMAT, Locale.getDefault())
            return oldFormat.format(Date(date))
        }

        fun string2Long(date: String): Long {
            val oldFormat = SimpleDateFormat(INPUT_FORMAT, Locale.getDefault())
            val created = oldFormat.parse(date)
            return created.time
        }
    }
}