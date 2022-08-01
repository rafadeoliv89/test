package com.example.test.core.network

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class Error(val message: String = "", val code: Int?): ResultWrapper<Nothing>()
}