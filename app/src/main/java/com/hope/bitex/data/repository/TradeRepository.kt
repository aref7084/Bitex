package com.hope.bitex.data.repository

import com.hope.bitex.data.api.TradeApiService
import com.hope.bitex.data.callApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TradeRepository @Inject constructor(
    private val apiService: TradeApiService,
) {



    suspend fun trade(
        token : String,
        amount : String
    ) = callApi {
        apiService.trade(token, amount)
    }





}