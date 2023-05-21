package com.tejpratapsingh.currencyconverter.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.tejpratapsingh.currencyconverter.R
import com.tejpratapsingh.currencyconverter.data.model.Currency
import com.tejpratapsingh.currencyconverter.data.response.Status
import com.tejpratapsingh.currencyconverter.databinding.FragmentHistoryBinding
import com.tejpratapsingh.currencyconverter.interfaces.OnCurrencySelectionListener
import com.tejpratapsingh.currencyconverter.ui.MainViewModel
import com.tejpratapsingh.currencyconverter.ui.currency.CurrencyListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val mainViewModel by activityViewModels<MainViewModel>()
    private val historyViewModel by viewModels<HistoryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonFrom = binding.cardConverterFrom
        val textFrom = binding.textConvertFromValue
        val lineChart = binding.chartHistory

        mainViewModel.baseCurrency.observe(viewLifecycleOwner) {
            if (it.name == textFrom.text) {
                return@observe
            }
            textFrom.text = it.name
            historyViewModel.getHistory(it)
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
        historyViewModel.referenceCurrency.observe(viewLifecycleOwner) {
            if (it.name == textTo.text) {
                return@observe
            }
            val baseCurrency = mainViewModel.baseCurrency.value ?: return@observe
            textTo.text = it.name
            historyViewModel.getHistory(baseCurrency)
        }

        buttonTo.setOnClickListener {
            CurrencyListFragment().also {
                it.show(childFragmentManager, CurrencyListFragment::class.java.simpleName)
                it.currencySelectionListener = object : OnCurrencySelectionListener {
                    override fun onCountrySelected(currency: Currency) {
                        historyViewModel.setReferenceCurrency(currency)
                    }
                }
            }
        }

        val progressBar = binding.progressBarHistory
        progressBar.visibility = View.INVISIBLE

        historyViewModel.timeseriesResponse.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.INVISIBLE
                    lineChart.visibility = View.VISIBLE
                    it.data?.let { it1 -> lineChart.setChartHistoryData(it1) }
                }

                Status.ERROR -> {
                    progressBar.visibility = View.INVISIBLE
                    lineChart.visibility = View.INVISIBLE
                    it.message?.let { message ->
                        Snackbar.make(
                            lineChart,
                            message,
                            Snackbar.LENGTH_INDEFINITE
                        )
                            .setAction(R.string.retry, View.OnClickListener {
                                val currency =
                                    mainViewModel.baseCurrency.value ?: return@OnClickListener
                                historyViewModel.getHistory(currency)
                            })
                            .show()
                    }
                }

                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    lineChart.visibility = View.VISIBLE
                }
            }
        }

        progressBar.visibility = View.INVISIBLE
    }
}