package com.example.pressurediary.ui.utils

import android.app.Activity
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