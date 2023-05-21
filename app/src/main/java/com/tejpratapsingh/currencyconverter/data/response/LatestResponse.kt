package com.tejpratapsingh.currencyconverter.data.response

data class LatestResponse(
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)