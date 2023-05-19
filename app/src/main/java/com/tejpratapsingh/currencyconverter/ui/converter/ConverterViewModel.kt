package com.tejpratapsingh.currencyconverter.ui.converter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tejpratapsingh.currencyconverter.data.model.Currency
import com.tejpratapsingh.currencyconverter.data.response.ConvertResponse
import com.tejpratapsingh.currencyconverter.data.response.Resource
import com.tejpratapsingh.currencyconverter.remote.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(private val repository: CurrencyRepository) : ViewModel() {

    private val _targetCurrency = MutableLiveData<Currency>().apply {
        value = Currency.USD
    }
    val targetCurrency: LiveData<Currency> = _targetCurrency

    fun setTargetCurrency(currency: Currency) {
        _targetCurrency.value = currency
    }

    private val _convertResponse = MutableLiveData<Resource<ConvertResponse>>().apply {
        value = Resource.error("Tap Convert", null)
    }
    val convertResponse: LiveData<Resource<ConvertResponse>> = _convertResponse

    fun convertToCurrency(from: Currency, to: Currency, amount: Float) = viewModelScope.launch {
        _convertResponse.value = Resource.loading(null)

        try {
            repository.convert(from, to, amount).apply {
                if (isSuccessful) {
                    _convertResponse.value = Resource.success(body())
                } else {
                    _convertResponse.value = Resource.error(message(), null)
                }
            }
        } catch (e: Exception) {
            _convertResponse.value = e.message?.let { Resource.error(it, null) }
        }
    }
}