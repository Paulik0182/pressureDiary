package com.example.pressurediary.ui.chart

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.pressurediary.R
import com.example.pressurediary.domain.BpEvaluation
import com.example.pressurediary.domain.interactors.BpEvaluator
import com.example.pressurediary.domain.repos.BpRepo
import com.example.pressurediary.ui.utils.getColor
import com.example.pressurediary.ui.utils.getColorFromAttr
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import org.koin.android.ext.android.inject

class ChartFragment : Fragment(R.layout.fragment_chart) {

    private lateinit var pieChart: PieChart

    private val bpRepo: BpRepo by inject()
    private val bpEvaluator: BpEvaluator by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pieChart = view.findViewById(R.id.pie_chart)

        bpRepo.getAllBpList {
            var normalBpCount = 0
            var preHypertensionBpCount = 0
            var hypertension1BpCount = 0
            var hypertension2BpCount = 0
            var unknownBpCount = 0
            it.forEach {
                when (bpEvaluator.evaluate(it)) {
                    BpEvaluation.NORMAL -> normalBpCount++
                    BpEvaluation.PRE_HYPERTENSION -> preHypertensionBpCount++
                    BpEvaluation.HYPERTENSION_1 -> hypertension1BpCount++
                    BpEvaluation.HYPERTENSION_2 -> hypertension2BpCount++
                    BpEvaluation.UNKNOWN -> unknownBpCount++
                }
            }

            val pieEntries = listOf(
                PieEntry(normalBpCount.toFloat(), getText(R.string.normal).toString()),
                PieEntry(
                    preHypertensionBpCount.toFloat(),
                    getText(R.string.prehypertension).toString()
                ),
                PieEntry(
                    hypertension1BpCount.toFloat(),
                    getText(R.string.hypertension_1).toString()
                ),
                PieEntry(
                    hypertension2BpCount.toFloat(),
                    getText(R.string.hypertension_2).toString()
                ),
                PieEntry(unknownBpCount.toFloat(), getText(R.string.unknown).toString())
            )

            val pieDataSet = PieDataSet(pieEntries, getText(R.string.pressure).toString())

            pieDataSet.colors = listOf(
                BpEvaluation.NORMAL.getColor(requireContext()),
                BpEvaluation.PRE_HYPERTENSION.getColor(requireContext()),
                BpEvaluation.HYPERTENSION_1.getColor(requireContext()),
                BpEvaluation.HYPERTENSION_2.getColor(requireContext()),
                BpEvaluation.UNKNOWN.getColor(requireContext())
            )

            val pieData = PieData(pieDataSet)
            pieData.setValueTextSize(requireContext().resources.getDimension(R.dimen.default_chart_text_size))
            pieData.setValueTextColor(requireContext().getColorFromAttr(R.attr.default_text))

            pieChart.data = pieData

            pieChart.isDrawHoleEnabled = false // Убрать отверстие
            pieChart.setEntryLabelColor(R.attr.default_text)//цвет всех надписей
            pieChart.setDrawEntryLabels(false)//отключили надписи
            pieChart.setUsePercentValues(true)//Использовать процентные соотношения %

            pieDataSet.valueFormatter = PercentFormatter(pieChart) //на графике рисуется значек %

            pieChart.description.text = getText(R.string.pressure_distribution).toString()
            pieChart.description.textSize =
                requireContext().resources.getDimension(R.dimen.default_chart_text_size)

            pieChart.setBackgroundColor(requireContext().getColorFromAttr(R.attr.second_plan_background))
        }
    }

    interface Controller {
        //TODO
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }
}