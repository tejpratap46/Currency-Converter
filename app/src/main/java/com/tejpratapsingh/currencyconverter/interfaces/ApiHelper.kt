package com.tejpratapsingh.currencyconverter.interfaces

import com.tejpratapsingh.currencyconverter.data.model.Currency
import com.tejpratapsingh.currencyconverter.data.response.ConvertResponse
import com.tejpratapsingh.currencyconverter.data.response.LatestRates
import retrofit2.Response

interface ApiHelper {
<<<<<<< HEAD
    suspend fun convert(from: Currency, to: Currency, amount: Float): Response<ConvertResponse>
    suspend fun latest(base: Currency): Response<LatestRates>
=======
    suspend fun convert(from: String, to: String, amount: Float): Response<ConvertResponse>
>>>>>>> origin/main
}