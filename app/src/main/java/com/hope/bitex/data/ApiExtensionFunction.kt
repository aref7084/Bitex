package com.hope.bitex.data

import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow

suspend fun <T> callApi(api: suspend () -> T)= channelFlow <ResponseModel<T>> {

  //  send(ResponseModel.Loading())
    try {
        val response=api.invoke()
        send(ResponseModel.Success(response))

    } catch (e: Exception) {

        send(ResponseModel.Error(e))

    }

}