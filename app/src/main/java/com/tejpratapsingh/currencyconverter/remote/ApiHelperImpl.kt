package com.tejpratapsingh.currencyconverter.remote

import com.tejpratapsingh.currencyconverter.data.model.Currency
import com.tejpratapsingh.currencyconverter.data.response.ConvertResponse
import com.tejpratapsingh.currencyconverter.data.response.LatestResponse
import com.tejpratapsingh.currencyconverter.data.response.TimeSeriesResponse
import com.tejpratapsingh.currencyconverter.extension.formatToApiDateStringFormat
import com.tejpratapsingh.currencyconverter.interfaces.ApiHelper
import com.tejpratapsingh.currencyconverter.interfaces.ApiService
import retrofit2.Response
import java.time.LocalDate
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

    override suspend fun latest(base: Currency): Response<LatestResponse> =
        apiService.latest(from = base.code)

    override suspend fun timeseries(
        base: Currency,
        start: LocalDate,
        end: LocalDate,
        referenceCurrency: Currency
    ): Response<TimeSeriesResponse> {
        val startDateString = start.formatToApiDateStringFormat()
        val endDateString = end.formatToApiDateStringFormat()

        return apiService.timeseries(
            currency = base.code,
            startDate = startDateString,
            endDate = endDateString,
            referenceCurrency = referenceCurrency.code
        )
    }
}