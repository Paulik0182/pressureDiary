package com.example.pressurediary.ui.chart

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.pressurediary.R
import com.example.pressurediary.domain.BpEvaluation
import com.example.pressurediary.domain.interactors.BpDaoInteractor
import com.example.pressurediary.domain.interactors.BpEvaluator
import com.example.pressurediary.domain.repos.BpRepo
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import org.koin.android.ext.android.inject
import org.koin.core.component.getScopeName

class ChartFragment : Fragment(R.layout.fragment_chart) {

    private lateinit var pieChart: PieChart

    private val bpRepo: BpRepo by inject() //получили через Koin
    private val bpEvaluator: BpEvaluator by inject() //получили enum значения через Koin

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pieChart = view.findViewById(R.id.pie_chart)

        val bpList = bpRepo.getAllBpList()//получили данные

        // делаем списки с состояниями (у нас есть типы оценки состояния) завели Счетчик
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
            PieEntry(normalBpCount.toFloat(), "Нормальное"),
            PieEntry(preHypertensionBpCount.toFloat(), "Предгипертензия"),
            PieEntry(hypertension1BpCount.toFloat(), "Гипертензия 1"),
            PieEntry(hypertension2BpCount.toFloat(), "Гипертензия 2"),
            PieEntry(unknownBpCount.toFloat(), "Не известно 1")
        )

        //получаем количество значений и название
        val pieDataSet = PieDataSet(pieEntries, "Давление")

        //Раскрашиваем значения. ВНИМАНИЕ на написание colors
        pieDataSet.colors = listOf(
            Color.GREEN,
            Color.CYAN,
            Color.YELLOW,
            Color.RED,
            Color.GRAY
        )

        //заводим сущьность pie data
        val pieData = PieData(pieDataSet)
        pieData.setValueTextSize(16f)//задали размер выведеных значений

        //принимает на вход некую сущьность (данные)
        pieChart.data = pieData

        //регулирования радиуса отверстия
//        pieChart.holeRadius = 0f
        pieChart.isDrawHoleEnabled = false // Убрать отверстие
//        pieChart.setCenterTextColor(Color.BLACK)//цвет значений (не названий)
        pieChart.setEntryLabelColor(Color.BLACK)//цвет всех надписей
        pieChart.setDrawEntryLabels(false)//отключили надписи
//        pieChart.setEntryLabelTextSize(22f)//размер только надписей
        pieChart.setUsePercentValues(true)//Использовать процентные соотношения %

        //Условное название графика
        pieChart.description.text = "Расспределение давления"
        pieChart.description.textSize = 16f//размер названия

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