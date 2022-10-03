package com.example.pressurediary.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

/**
 * Специально для форматирования значений. Форматируется дата и время.
 */

@SuppressLint("SimpleDateFormat")
var bpTimeFormatter = SimpleDateFormat("HH:mm")
@SuppressLint("SimpleDateFormat")
var bpDataFormatter = SimpleDateFormat("dd.MM.yyyy")