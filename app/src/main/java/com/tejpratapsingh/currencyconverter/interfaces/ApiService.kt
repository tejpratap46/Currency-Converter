package com.tejpratapsingh.currencyconverter.interfaces

import com.tejpratapsingh.currencyconverter.BuildConfig
import com.tejpratapsingh.currencyconverter.data.response.ConvertResponse
import com.tejpratapsingh.currencyconverter.data.response.LatestRates
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("convert")
    suspend fun convert(
        @Header("apikey") apiKey: String = BuildConfig.API_KEY,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Float
    ): Response<ConvertResponse>

    @GET("latest")
    suspend fun latest(
        @Header("apikey") apiKey: String = BuildConfig.API_KEY,
        @Query("base") from: String
    ): Response<LatestRates>
}