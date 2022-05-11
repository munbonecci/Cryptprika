package com.munbonecci.cryptprika.common

sealed class Resource<out T> {
    data class Success<out T>(val value: T?) : Resource<T>()
    data class Error(val code: Int? = null, val message: String? = null) : Resource<Nothing>()
    object NetworkError : Resource<Nothing>()
}