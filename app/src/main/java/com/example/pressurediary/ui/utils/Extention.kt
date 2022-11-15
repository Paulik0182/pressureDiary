package com.example.pressurediary.ui.utils

import android.app.Activity
import android.text.TextUtils
import android.view.View
import android.widget.Toast

fun View.toastFragment(text: String) {
    Toast.makeText(
        context,
        text,
        Toast.LENGTH_SHORT
    )
        .show()
}

fun Activity.toastActivity(text: String) {
    Toast.makeText(
        this,
        text,
        Toast.LENGTH_SHORT
    )
        .show()
}

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) &&
            android
                .util
                .Patterns
                .EMAIL_ADDRESS
                .matcher(this)
                .matches()
}

fun String.isPasswordValid(): Boolean {
    if (length < 6 && isEmpty()) return false
    return true
}