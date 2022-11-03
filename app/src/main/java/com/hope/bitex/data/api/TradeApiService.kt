package com.hope.bitex.data.api

import retrofit2.http.*

interface TradeApiService {

    @FormUrlEncoded
    @POST("order")
    suspend fun trade(
        @Field("token") token : String,
        @Field("amount") amount : String
    ): Any

}