package com.wisnumkt.capstone1.core.utils

sealed class Result<out T: Any?> {

    object Loading : Result<Nothing>()

    data class Success<out T: Any>(val data: T) : Result<T>()

    data class Error(val message: String?, val exception: Exception? = null) : Result<Nothing>()
}