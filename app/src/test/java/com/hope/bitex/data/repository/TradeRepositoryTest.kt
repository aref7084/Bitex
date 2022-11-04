package com.hope.bitex.data.repository

import com.hope.bitex.BaseUnitTest
import com.hope.bitex.data.api.TradeApiService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class TradeRepositoryTest : BaseUnitTest() {
    private lateinit var repository: TradeRepository
    private val api: TradeApiService = mock()
    private val token = "BTC"
    private val amount = "100"


    @Test
    fun fetchTradeFromAPI() = runTest {
        repository = TradeRepository(api)
        repository.trade(amount, token).single()
        verify(api, times(1)).trade(amount, token)

    }
}