package com.tejpratapsingh.currencyconverter.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tejpratapsingh.currencyconverter.data.model.Currency
import com.tejpratapsingh.currencyconverter.data.response.LatestResponse
import com.tejpratapsingh.currencyconverter.data.response.Resource
import com.tejpratapsingh.currencyconverter.remote.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: CurrencyRepository) : ViewModel() {

    private val _exchangeResponse = MutableLiveData<Resource<LatestResponse>>().apply {
        value = Resource.error("Tap Convert", null)
    }
    val exchangeResponse: LiveData<Resource<LatestResponse>> = _exchangeResponse

    fun convertToCurrency(from: Currency) = viewModelScope.launch {
        _exchangeResponse.value = Resource.loading(null)

        try {
            repository.latest(from).apply {
                if (isSuccessful) {
                    _exchangeResponse.value = Resource.success(body())
                } else {
                    _exchangeResponse.value = Resource.error(message(), null)
                }
            }
        } catch (e: Exception) {
            _exchangeResponse.value = e.message?.let { Resource.error(it, null) }
        }
    }

}