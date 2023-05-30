package com.tejpratapsingh.currencyconverter.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tejpratapsingh.currencyconverter.data.model.Currency
import com.tejpratapsingh.currencyconverter.interfaces.NetworkConnectivityService
import com.tejpratapsingh.currencyconverter.interfaces.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(networkConnectivityService: NetworkConnectivityService) :
    ViewModel() {
    private val _baseCurrency = MutableLiveData<Currency>().apply {
        value = Currency.INR
    }
    val baseCurrency: LiveData<Currency> = _baseCurrency

    fun setBaseCurrency(currency: Currency) {
        _baseCurrency.value = currency
    }

    val networkStatus: StateFlow<NetworkStatus> = networkConnectivityService.networkStatus.stateIn(
        initialValue = NetworkStatus.Unknown,
        scope = viewModelScope,
        started = WhileSubscribed(5000)
    )
}