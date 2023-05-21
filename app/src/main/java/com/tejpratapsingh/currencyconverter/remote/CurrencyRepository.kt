package com.tejpratapsingh.currencyconverter.remote

import com.tejpratapsingh.currencyconverter.data.model.Currency
import com.tejpratapsingh.currencyconverter.interfaces.ApiHelper
import java.time.LocalDate
import java.util.Date
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun convert(from: Currency, to: Currency, amount: Float) =
        apiHelper.convert(from, to, amount)

    suspend fun latest(base: Currency) = apiHelper.latest(base)

    suspend fun timeseries(
        base: Currency,
        start: LocalDate,
        end: LocalDate,
        referenceCurrency: Currency
    ) = apiHelper.timeseries(base, start, end, referenceCurrency)
}