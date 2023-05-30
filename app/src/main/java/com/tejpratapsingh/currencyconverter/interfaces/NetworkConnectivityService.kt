package com.tejpratapsingh.currencyconverter.interfaces

import kotlinx.coroutines.flow.Flow

interface NetworkConnectivityService {
    val networkStatus: Flow<NetworkStatus>
}