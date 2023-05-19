package com.tejpratapsingh.currencyconverter.interfaces

import com.tejpratapsingh.currencyconverter.data.model.Currency

interface OnCurrencySelectionListener {
    fun onCountrySelected(currency: Currency)
}