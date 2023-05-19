package com.tejpratapsingh.currencyconverter.remote

import com.tejpratapsingh.currencyconverter.data.model.Currency
import com.tejpratapsingh.currencyconverter.data.response.ConvertResponse
import com.tejpratapsingh.currencyconverter.data.response.LatestRates
import com.tejpratapsingh.currencyconverter.interfaces.ApiHelper
import com.tejpratapsingh.currencyconverter.interfaces.ApiService
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {
    override suspend fun convert(
        from: Currency,
        to: Currency,
        amount: Float
    ): Response<ConvertResponse> =
        apiService.convert(from = from.code, to = to.code, amount = amount)

    override suspend fun latest(base: Currency): Response<LatestRates> =
        apiService.latest(from = base.code)
}