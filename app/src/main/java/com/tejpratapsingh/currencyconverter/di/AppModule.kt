package com.tejpratapsingh.currencyconverter.di

import android.content.Context
import com.tejpratapsingh.currencyconverter.BuildConfig
import com.tejpratapsingh.currencyconverter.interfaces.ApiHelper
import com.tejpratapsingh.currencyconverter.interfaces.ApiService
import com.tejpratapsingh.currencyconverter.interfaces.NetworkConnectivityService
import com.tejpratapsingh.currencyconverter.remote.ApiHelperImpl
import com.tejpratapsingh.currencyconverter.remote.NetworkConnectivityServiceImpl
import com.tejpratapsingh.currencyconverter.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideNetworkConnectivityService(@ApplicationContext appContext: Context): NetworkConnectivityService =
        NetworkConnectivityServiceImpl(appContext)
}