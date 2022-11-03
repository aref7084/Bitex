package com.hope.bitex.data


sealed class ResponseModel<T>(
    val data: T? = null,
    val exception: Exception? = null
) {
    class Loading<T>() : ResponseModel<T>()
    class Success<T>(data: T) : ResponseModel<T>(data)
    class Error<T>(exception: Exception?) : ResponseModel<T>(null, exception)
}