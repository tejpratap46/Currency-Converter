package com.tejpratapsingh.currencyconverter.ui.converter

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.core.text.scale
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.tejpratapsingh.currencyconverter.R
import com.tejpratapsingh.currencyconverter.data.model.Currency
import com.tejpratapsingh.currencyconverter.data.response.Status
import com.tejpratapsingh.currencyconverter.databinding.FragmentConverterBinding
import com.tejpratapsingh.currencyconverter.interfaces.OnCurrencySelectionListener
import com.tejpratapsingh.currencyconverter.ui.MainViewModel
import com.tejpratapsingh.currencyconverter.ui.currency.CurrencyListFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class ConverterFragment : Fragment() {

    private var _binding: FragmentConverterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val mainViewModel by activityViewModels<MainViewModel>()
    private val converterViewModel by viewModels<ConverterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConverterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonFrom = binding.cardConverterFrom
        val textFrom = binding.textConvertFromValue
        val textResult = binding.textConvertResult

        mainViewModel.baseCurrency.observe(viewLifecycleOwner) {
            textFrom.text = it.name
            val toCurrency = converterViewModel.targetCurrency.value ?: return@observe
            getFormattedExchangeRateString(it, toCurrency, null).also { result ->
                textResult.text = result
            }
        }

        buttonFrom.setOnClickListener {
            CurrencyListFragment().also {
                it.show(childFragmentManager, CurrencyListFragment::class.java.simpleName)
                it.currencySelectionListener = object : OnCurrencySelectionListener {
                    override fun onCountrySelected(currency: Currency) {
                        mainViewModel.setBaseCurrency(currency)
                    }
                }
            }
        }

        val buttonTo = binding.cardConverterTo
        val textTo = binding.textConvertToValue
        converterViewModel.targetCurrency.observe(viewLifecycleOwner) {
            textTo.text = it.name
            val fromCurrency = mainViewModel.baseCurrency.value ?: return@observe
            getFormattedExchangeRateString(fromCurrency, it, null).also { result ->
                textResult.text = result
            }
        }

        buttonTo.setOnClickListener {
            CurrencyListFragment().also {
                it.show(childFragmentManager, CurrencyListFragment::class.java.simpleName)
                it.currencySelectionListener = object : OnCurrencySelectionListener {
                    override fun onCountrySelected(currency: Currency) {
                        converterViewModel.setTargetCurrency(currency)
                    }
                }
            }
        }

        val progressBar = binding.progressBarCurrencyConvert
        progressBar.visibility = View.INVISIBLE

        converterViewModel.convertResponse.observe(viewLifecycleOwner) {
            val fromCurrency = mainViewModel.baseCurrency.value ?: return@observe
            val toCurrency = converterViewModel.targetCurrency.value ?: return@observe
            when (it.status) {
                Status.SUCCESS -> {
                    getFormattedExchangeRateString(
                        fromCurrency,
                        toCurrency,
                        it.data?.result
                    ).also { result ->
                        textResult.text = result
                    }
                    progressBar.visibility = View.INVISIBLE
                }

                Status.ERROR -> {
                    textResult.text = it.message.toString()
                    progressBar.visibility = View.INVISIBLE
                }

                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        }

        progressBar.visibility = View.INVISIBLE

        val buttonConvert = binding.buttonConvert
        buttonConvert.setOnClickListener {
            buttonConvert.isEnabled = false
            val fromCurrency = mainViewModel.baseCurrency.value ?: return@setOnClickListener
            val toCurrency = converterViewModel.targetCurrency.value ?: return@setOnClickListener
            converterViewModel.convertToCurrency(fromCurrency, toCurrency, 1f)
            buttonConvert.isEnabled = true
        }
    }

    private fun getFormattedExchangeRateString(
        fromCurrency: Currency,
        toCurrency: Currency,
        value: Double?
    ): Spanned {
        val formattedValue = value?.let {
            String.format(Locale.getDefault(), "%f", value)
        }
        val spanned = SpannableStringBuilder()
            .append(fromCurrency.code)
            .append(" ")
            .bold {
                scale(1.5f) { append("${1}") }
            }
            .append(getString(R.string.equals))
            .append(toCurrency.code)
            .append(" ")
            .bold {
                scale(1.5f) { append(formattedValue ?: "???") }
            }
        return spanned
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}