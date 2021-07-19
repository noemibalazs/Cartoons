package com.urban.androidhomework.utils

import android.content.Context
import android.widget.Toast

fun Context.showErrorToastToUser(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}