package com.example.pressurediary.ui.chart

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.pressurediary.R
import com.example.pressurediary.domain.BpEvaluation
import com.example.pressurediary.domain.interactors.BpEvaluator
import com.example.pressurediary.domain.repos.BpRepo
import com.example.pressurediary.ui.utils.getColor
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import org.koin.android.ext.android.inject

private const val DEFAULT_CHART_TEXT_SIZE = 16f

class ChartFragment : Fragment(R.layout.fragment_chart) {

    private lateinit var pieChart: PieChart

    private val bpRepo: BpRepo by inject() //получили через Koin
    private val bpEvaluator: BpEvaluator by inject() //получили enum значения через Koin

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pieChart = view.findViewById(R.id.pie_chart)

        val bpList = bpRepo.getAllBpList()//получили данные

        //ВАРИАНТ 1 более подробный
        // (это *Табличка) делаем списки с состояниями (у нас есть типы оценки состояния) завели Счетчик
        var normalBpCount = 0
        var preHypertensionBpCount = 0
        var hypertension1BpCount = 0
        var hypertension2BpCount = 0
        var unknownBpCount = 0

        // заполняем данными (прошлись по всем, в зависимости от оценки поставили оценку)
        bpList.forEach {
            when (bpEvaluator.evaluate(it)) {
                BpEvaluation.NORMAL -> normalBpCount++
                BpEvaluation.PRE_HYPERTENSION -> preHypertensionBpCount++
                BpEvaluation.HYPERTENSION_1 -> hypertension1BpCount++
                BpEvaluation.HYPERTENSION_2 -> hypertension2BpCount++
                BpEvaluation.UNKNOWN -> unknownBpCount++
            }
        }

        //Заводим некий лист входных псевдо данных
        val pieEntries = listOf<PieEntry>(
            PieEntry(normalBpCount.toFloat(), getText(R.string.normal).toString()),
            PieEntry(preHypertensionBpCount.toFloat(), getText(R.string.prehypertension).toString()),
            PieEntry(hypertension1BpCount.toFloat(), getText(R.string.hypertension_1).toString()),
            PieEntry(hypertension2BpCount.toFloat(), getText(R.string.hypertension_2).toString()),
            PieEntry(unknownBpCount.toFloat(), getText(R.string.unknown).toString())
        )

        //получаем количество значений и название
        val pieDataSet = PieDataSet(pieEntries, getText(R.string.pressure).toString())

        //Вариант 3 (расширяем функцию. экстеншен)
        //Раскрашиваем значения. ВНИМАНИЕ на написание colors
        pieDataSet.colors = listOf(
            BpEvaluation.NORMAL.getColor(requireContext()),
            BpEvaluation.PRE_HYPERTENSION.getColor(requireContext()),
            BpEvaluation.HYPERTENSION_1.getColor(requireContext()),
            BpEvaluation.HYPERTENSION_2.getColor(requireContext()),
            BpEvaluation.UNKNOWN.getColor(requireContext())
        )

        //заводим сущьность pie data
        val pieData = PieData(pieDataSet)
        pieData.setValueTextSize(DEFAULT_CHART_TEXT_SIZE)//задали размер выведеных значений

        //принимает на вход некую сущьность (данные)
        pieChart.data = pieData

        //регулирования радиуса отверстия
//        pieChart.holeRadius = 0f
        pieChart.isDrawHoleEnabled = false // Убрать отверстие
//        pieChart.setCenterTextColor(Color.BLACK)//цвет значений (не названий)
//        pieChart.setEntryLabelColor(Color.BLACK)//цвет всех надписей
        pieChart.setEntryLabelColor(R.attr.default_text)//цвет всех надписей
        pieChart.setDrawEntryLabels(false)//отключили надписи
//        pieChart.setEntryLabelTextSize(22f)//размер только надписей
        pieChart.setUsePercentValues(true)//Использовать процентные соотношения %

        //Если не инициализировать свою PercentFormatter круговую диаграмму, она не будет отображать %
        // Вариант 1
//        pieData.setValueFormatter(PercentFormatter(pieChart))//отформатировали значения (на графике рисуется значек %)
        // Вариант 2
        pieDataSet.valueFormatter = PercentFormatter(pieChart) //на графике рисуется значек %

        //Условное название графика
        pieChart.description.text = getText(R.string.pressure_distribution).toString()
        pieChart.description.textSize = DEFAULT_CHART_TEXT_SIZE//размер названия

        //Цвет фона
        pieChart.setBackgroundColor(Color.WHITE)
    }

    interface Controller {
        //TODO
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ChartFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }
}