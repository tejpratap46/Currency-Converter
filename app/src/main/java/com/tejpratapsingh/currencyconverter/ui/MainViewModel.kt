package com.tejpratapsingh.currencyconverter.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tejpratapsingh.currencyconverter.data.model.Currency

class MainViewModel: ViewModel() {
    private val _baseCurrency = MutableLiveData<Currency>().apply {
        value = Currency.INR
    }
    val baseCurrency: LiveData<Currency> = _baseCurrency

    fun setBaseCurrency(currency: Currency) {
        _baseCurrency.value = currency
    }
}