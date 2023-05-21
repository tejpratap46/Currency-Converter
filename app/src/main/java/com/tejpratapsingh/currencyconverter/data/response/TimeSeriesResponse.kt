package com.tejpratapsingh.currencyconverter.data.response

data class TimeSeriesResponse(
    val success: Boolean,
    val timeseries: Boolean,
    val startDate: String,
    val endDate: String,
    val base: String,
    val rates: Map<String, Map<String, Double>>
)
