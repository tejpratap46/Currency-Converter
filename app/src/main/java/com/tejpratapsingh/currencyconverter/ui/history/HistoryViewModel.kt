package com.tejpratapsingh.currencyconverter.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tejpratapsingh.currencyconverter.data.model.Currency
import com.tejpratapsingh.currencyconverter.data.response.Resource
import com.tejpratapsingh.currencyconverter.data.response.TimeSeriesResponse
import com.tejpratapsingh.currencyconverter.remote.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val repository: CurrencyRepository) : ViewModel() {

    private val _referenceCurrency = MutableLiveData<Currency>().apply {
        value = Currency.USD
    }
    val referenceCurrency: LiveData<Currency> = _referenceCurrency

    fun setReferenceCurrency(currency: Currency) {
        _referenceCurrency.value = currency
    }

    private val _timeseriesResponse = MutableLiveData<Resource<TimeSeriesResponse>>().apply {
        value = Resource.error("Tap Convert", null)
    }
    val timeseriesResponse: LiveData<Resource<TimeSeriesResponse>> = _timeseriesResponse

    fun getHistory(base: Currency) = viewModelScope.launch {
        val referenceCurrency = referenceCurrency.value ?: return@launch

        _timeseriesResponse.value = Resource.loading(null)

        val startDate = LocalDate.now().minusWeeks(1)
        val endDate = LocalDate.now()

        try {
            repository.timeseries(base, startDate, endDate, referenceCurrency).apply {
                if (isSuccessful) {
                    _timeseriesResponse.value = Resource.success(body())
                } else {
                    _timeseriesResponse.value = Resource.error(message(), null)
                }
            }
        } catch (e: Exception) {
            _timeseriesResponse.value = e.message?.let { Resource.error(it, null) }
        }
    }

}