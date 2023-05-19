package com.tejpratapsingh.currencyconverter.remote

import com.tejpratapsingh.currencyconverter.data.model.Currency
import com.tejpratapsingh.currencyconverter.interfaces.ApiHelper
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val apiHelper: ApiHelper
<<<<<<< HEAD
) {
    suspend fun convert(from: Currency, to: Currency, amount: Float) =
        apiHelper.convert(from, to, amount)

    suspend fun latest(base: Currency) = apiHelper.latest(base)
=======
){
    suspend fun convert(from: String, to: String, amount: Float) = apiHelper.convert(from, to, amount)
>>>>>>> origin/main
}