package com.tejpratapsingh.currencyconverter.ui.home

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
import com.tejpratapsingh.currencyconverter.data.response.Status.ERROR
import com.tejpratapsingh.currencyconverter.data.response.Status.LOADING
import com.tejpratapsingh.currencyconverter.data.response.Status.SUCCESS
import com.tejpratapsingh.currencyconverter.databinding.FragmentHomeBinding
import com.tejpratapsingh.currencyconverter.interfaces.OnCurrencySelectionListener
import com.tejpratapsingh.currencyconverter.ui.MainViewModel
import com.tejpratapsingh.currencyconverter.ui.currency.CurrencyListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val mainViewModel by activityViewModels<MainViewModel>()
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonFromCurrency = binding.cardHome
        val textFrom = binding.textConvertFromValue
        mainViewModel.baseCurrency.observe(viewLifecycleOwner) {
            if (textFrom.text == it.name) {
                return@observe
            }
            textFrom.text = it.name
            getLatestRates(it)
        }

        val progress = binding.progressBarHome
        progress.visibility = View.INVISIBLE
        val recyclerView = binding.recyclerViewCurrencyList
        val adapter = HomeCurrencyAdapter()
        recyclerView.adapter = adapter

        buttonFromCurrency.setOnClickListener {
            CurrencyListFragment().also {
                it.show(childFragmentManager, CurrencyListFragment::class.java.simpleName)
                it.currencySelectionListener = object : OnCurrencySelectionListener {
                    override fun onCountrySelected(currency: Currency) {
                        mainViewModel.setBaseCurrency(currency)
                    }
                }
            }
        }

        homeViewModel.convertResponse.observe(viewLifecycleOwner) {
            when (it.status) {
                SUCCESS -> {
                    it.data?.let { it1 -> adapter.updatedLatestRates(it1) }
                    progress.visibility = View.INVISIBLE
                }

                ERROR -> {
                    progress.visibility = View.INVISIBLE
                    adapter.clear()
                    it.message?.let { message ->
                        Snackbar.make(
                            recyclerView,
                            message,
                            Snackbar.LENGTH_INDEFINITE
                        )
                            .setAction(R.string.retry, View.OnClickListener {
                                val currency =
                                    mainViewModel.baseCurrency.value ?: return@OnClickListener
                                getLatestRates(currency)
                            })
                            .show()
                    }
                }

                LOADING -> {
                    progress.visibility = View.VISIBLE
                    adapter.clear()
                }
            }
        }
    }

    private fun getLatestRates(currency: Currency) {
        homeViewModel.convertToCurrency(currency)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}