package com.dhbw.tinf20ai.cryptotracker.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.dhbw.tinf20ai.cryptotracker.api.CoinCapApi
import com.dhbw.tinf20ai.cryptotracker.api.model.AssetPriceHistory
import com.dhbw.tinf20ai.cryptotracker.databinding.FragmentDetailBinding
import com.dhbw.tinf20ai.cryptotracker.model.AssetSharedViewModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.Utils
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import com.github.mikephil.charting.components.Legend

import android.R

import androidx.core.content.ContextCompat

import android.graphics.drawable.Drawable
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.ValueFormatter


class DetailFragment : Fragment() {

    private val model: AssetSharedViewModel by activityViewModels()

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.selectedAsset.observe(viewLifecycleOwner, Observer {
            // TODO: Show the details of the given asset
            // TODO: also initialize the chart!
        })
    }

    private fun initializeLineChart(prices: Array<AssetPriceHistory>) {
        binding.lineChart.setTouchEnabled(false);
        binding.lineChart.setPinchZoom(false);

        binding.lineChart.description.isEnabled = false
        binding.lineChart.xAxis.setDrawGridLines(false)
        binding.lineChart.axisRight.isEnabled = false

        val entries: ArrayList<Entry> = ArrayList(prices.mapIndexed { index, price ->
            Entry(index.toFloat(), price.priceUsd.toFloat(), price.time)
        })

        val lineDataSet = LineDataSet(entries, "")
        lineDataSet.lineWidth = 5f
        lineDataSet.color = Color.GRAY
        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawCircles(false)

        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        lineDataSet.cubicIntensity = 0.2f
        lineDataSet.setDrawFilled(true)
        lineDataSet.fillColor = Color.BLACK
        lineDataSet.fillAlpha = 80

        val iLineDataSetArrayList: ArrayList<ILineDataSet> = ArrayList()
        iLineDataSetArrayList.add(lineDataSet)

        val lineData = LineData(iLineDataSetArrayList)
        lineData.setValueTextSize(13f)
        lineData.setValueTextColor(Color.BLACK)


        binding.lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        binding.lineChart.xAxis.labelCount = 3
        binding.lineChart.xAxis.valueFormatter = object: ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return formatInstant(prices[value.toInt()].time)
            }
        }

        binding.lineChart.legend.isEnabled = false
        binding.lineChart.data = lineData
        binding.lineChart.invalidate()
    }

    private fun formatInstant(instant: Instant): String {
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy")
            .withZone(ZoneId.systemDefault())

        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).format(dateTimeFormatter)
    }

}