package com.tejpratapsingh.currencyconverter.ui.currency

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.widget.addTextChangedListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tejpratapsingh.currencyconverter.data.model.Currency
import com.tejpratapsingh.currencyconverter.databinding.FragmentCountryListBinding
import com.tejpratapsingh.currencyconverter.interfaces.OnCurrencySelectionListener

class CurrencyListFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCountryListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var currencySelectionListener: OnCurrencySelectionListener? = null
    var localCurrencySelectionListener: OnCurrencySelectionListener =
        object : OnCurrencySelectionListener {
            override fun onCountrySelected(currency: Currency) {
                currencySelectionListener?.onCountrySelected(currency)
                dismissAllowingStateLoss()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerViewCurrencyList
        val currencyAdapter = CurrencyAdapter(localCurrencySelectionListener)
        recyclerView.adapter = currencyAdapter

        val searchBox = binding.textInputSearch
        searchBox.addTextChangedListener {
            currencyAdapter.filter(it.toString())
        }
        searchBox.requestFocus()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { view ->
                val behaviour = BottomSheetBehavior.from(view)
                setupFullHeight(view)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}