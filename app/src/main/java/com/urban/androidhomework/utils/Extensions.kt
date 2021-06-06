package com.urban.androidhomework.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast

fun Context.launchActivity(dest: Class<*>){
    this.startActivity(Intent(this, dest))
}

fun Context.showErrorToastToUser(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}