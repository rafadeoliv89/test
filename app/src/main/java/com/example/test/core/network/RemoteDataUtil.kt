package com.example.test.core.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend inline fun <reified T> call(crossinline apiCall: suspend () -> T): ResultWrapper<T> {

    val errorTag = "ERROR_API"

    return withContext(Dispatchers.IO) {
        try {
            val result = apiCall.invoke()
            ResultWrapper.Success(result)

        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    val code = throwable.code()
                    ResultWrapper.Error(code = code, message = errorTag)
                }
                else -> {
                    ResultWrapper.Error(code = null, message = errorTag)
                }
            }
        }
    }
}
