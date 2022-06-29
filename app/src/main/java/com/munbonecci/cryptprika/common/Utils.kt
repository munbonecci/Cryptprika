package com.munbonecci.cryptprika.common

import android.util.Log
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun String.formatAsCurrency(): String {
    return NumberFormat.getCurrencyInstance(Locale.US).format(this.toDoubleOrNull() ?: "")
}

fun getCurrentDate(): String {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
    val formatted = current.format(formatter)
    Log.d("Current date: ", formatted)
    return formatted
}