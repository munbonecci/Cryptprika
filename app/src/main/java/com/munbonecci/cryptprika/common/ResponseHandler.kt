package com.munbonecci.cryptprika.common

import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ResponseHandler @Inject constructor() {

    suspend operator fun <T : Any> invoke(apiCall: suspend () -> T): Resource<T> {
        return try {
            Resource.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            val errorMessage = throwable.localizedMessage
            when (throwable) {
                is IOException -> Resource.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    Resource.Error(code, errorMessage)
                }
                else -> {
                    Resource.Error(null, null)
                }
            }
        }
    }
}