package com.tejpratapsingh.currencyconverter.ui.currency

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tejpratapsingh.currencyconverter.data.model.Currency
import com.tejpratapsingh.currencyconverter.databinding.ItemCurrencyBinding
import com.tejpratapsingh.currencyconverter.interfaces.OnCurrencySelectionListener

class CurrencyAdapter(private val currencySelectionListener: OnCurrencySelectionListener?) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    private var currencyFilterList = Currency.asList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyAdapter.CurrencyViewHolder {
        val itemBinding =
            ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CurrencyViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return currencyFilterList.size
    }

    override fun onBindViewHolder(holder: CurrencyAdapter.CurrencyViewHolder, position: Int) {
        val currency = currencyFilterList[position]

        holder.viewBinding.textCurrencyCode.text = currency.code
        holder.viewBinding.textCurrencyName.text = currency.name

        holder.itemView.setOnClickListener {
            currencySelectionListener?.onCountrySelected(currency)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filter(text: String) {
        if (text.isEmpty()) {
            currencyFilterList = Currency.asList()
            notifyDataSetChanged()
            return
        }
        currencyFilterList = currencyFilterList.filter {
            it.name.contains(text, true) || it.code.contains(text, true)
        }
        notifyDataSetChanged()
    }

    inner class CurrencyViewHolder(val viewBinding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(viewBinding.root)
}