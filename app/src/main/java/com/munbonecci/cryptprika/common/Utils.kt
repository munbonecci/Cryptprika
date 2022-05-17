package com.munbonecci.cryptprika.common

import java.text.NumberFormat
import java.util.*

fun String.formatAsCurrency(): String {
    return NumberFormat.getCurrencyInstance(Locale.US).format(this.toDoubleOrNull() ?: "")
}