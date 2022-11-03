package com.hope.bitex.data

import kotlinx.coroutines.flow.flow

suspend fun <T> callApi(api: suspend () -> T)= flow<ResponseModel<T>> {

    emit(ResponseModel.Loading())
    try {
        val response=api.invoke()
        emit(ResponseModel.Success(response))

    } catch (e: Exception) {

        emit(ResponseModel.Error(e))

    }

}