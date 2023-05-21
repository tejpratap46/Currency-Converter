package com.tejpratapsingh.currencyconverter.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.tejpratapsingh.currencyconverter.R
import com.tejpratapsingh.currencyconverter.data.response.TimeSeriesResponse
import com.tejpratapsingh.currencyconverter.databinding.ViewHistoryChartBinding
import com.tejpratapsingh.currencyconverter.extension.dateFromApiDateString
import com.tejpratapsingh.currencyconverter.extension.formatToApiDateStringFormat
import com.tejpratapsingh.currencyconverter.extension.formatToDbDateStringFormat
import com.tejpratapsingh.currencyconverter.extension.getDateFromDbDate

class HistoryChartView(
    context: Context?,
    attrs: AttributeSet? = null,
) : LinearLayout(context, attrs) {

    private var binding: ViewHistoryChartBinding
    private var lineChart: LineChart

    init {
        binding = ViewHistoryChartBinding.inflate(LayoutInflater.from(context), this, true)

        lineChart = binding.lineChartHistory

        setUpLineChart()
    }

    /**
     * Set chart data
     */
    private fun setUpLineChart() {
        with(lineChart) {
            animateX(1200, Easing.EaseInSine)
            description.isEnabled = false

            xAxis.setDrawGridLines(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.valueFormatter = object : IndexAxisValueFormatter() {
                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                    val dbDate = value.toInt()
                    return dbDate.getDateFromDbDate().formatToApiDateStringFormat()
                }
            }

            axisRight.isEnabled = false

            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            legend.textSize = 15F
            legend.form = Legend.LegendForm.LINE
        }
    }

    fun setChartHistoryData(timeSeriesResponse: TimeSeriesResponse) {
        val timeSeriesEntries = timeSeriesResponse.rates.map {
            Entry(it.key.dateFromApiDateString().formatToDbDateStringFormat().toFloat(),
                it.value.firstNotNullOf { rate -> rate.value.toFloat() })
        }

        val dataSet = LineDataSet(timeSeriesEntries, "Rate")
        dataSet.lineWidth = 3f
        dataSet.valueTextSize = 15f
        dataSet.color = ContextCompat.getColor(context, androidx.appcompat.R.color.accent_material_light)
        dataSet.valueTextColor = ContextCompat.getColor(context, R.color.text_default)

        val lineData = LineData(dataSet)
        lineChart.data = lineData

        lineChart.offsetTopAndBottom((dataSet.yMax * 2f).toInt())

        lineChart.invalidate()
    }
}