package com.tejpratapsingh.currencyconverter.interfaces

import com.tejpratapsingh.currencyconverter.data.model.Currency
import com.tejpratapsingh.currencyconverter.data.response.ConvertResponse
import com.tejpratapsingh.currencyconverter.data.response.LatestResponse
import com.tejpratapsingh.currencyconverter.data.response.TimeSeriesResponse
import retrofit2.Response
import java.time.LocalDate
import java.util.Date

interface ApiHelper {
    suspend fun convert(from: Currency, to: Currency, amount: Float): Response<ConvertResponse>
    suspend fun latest(base: Currency): Response<LatestResponse>
    suspend fun timeseries(base: Currency, start: LocalDate, end: LocalDate, referenceCurrency: Currency): Response<TimeSeriesResponse>
}