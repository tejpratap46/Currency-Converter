package com.tejpratapsingh.currencyconverter.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tejpratapsingh.currencyconverter.data.model.Currency
import com.tejpratapsingh.currencyconverter.data.response.LatestResponse
import com.tejpratapsingh.currencyconverter.databinding.ItemCurrencyBinding
import com.tejpratapsingh.currencyconverter.interfaces.OnCurrencySelectionListener
import java.util.Locale

class HomeCurrencyAdapter(
    private var latestResponse: LatestResponse? = null,
    private val currencySelectionListener: OnCurrencySelectionListener? = null
) : RecyclerView.Adapter<HomeCurrencyAdapter.CurrencyViewHolder>() {

    private var currencyFilterList = Currency.asList()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): HomeCurrencyAdapter.CurrencyViewHolder {
        val itemBinding =
            ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CurrencyViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return currencyFilterList.size
    }

    override fun onBindViewHolder(holder: HomeCurrencyAdapter.CurrencyViewHolder, position: Int) {
        val currency = currencyFilterList[position]

        holder.viewBinding.textCurrencyCode.text = currency.code
        holder.viewBinding.textCurrencyName.text = currency.name
        holder.viewBinding.textCurrencyValue.text = "???"

        latestResponse?.rates?.get(currency.code)?.let {
            holder.viewBinding.textCurrencyValue.text =
                String.format(Locale.getDefault(), "%f", it)
        }

        holder.itemView.setOnClickListener {
            currencySelectionListener?.onCountrySelected(currency)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updatedLatestRates(latestResponse: LatestResponse) {
        this.latestResponse = latestResponse
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        this.latestResponse = null
        notifyDataSetChanged()
    }

    inner class CurrencyViewHolder(val viewBinding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(viewBinding.root)
}