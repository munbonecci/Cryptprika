package com.munbonecci.cryptprika.common

import com.munbonecci.cryptprika.R

data class Error(
    val message: String? = null,
    val resourceId: Int = R.string.generic_error,
)