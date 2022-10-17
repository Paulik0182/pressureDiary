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
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import org.koin.android.ext.android.inject

class ChartFragment : Fragment(R.layout.fragment_chart) {

    private lateinit var pieChart: PieChart

    private val bpRepo: BpRepo by inject() //получили через Koin
    private val bpEvaluator: BpEvaluator by inject() //получили enum значения через Koin

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pieChart = view.findViewById(R.id.pie_chart)

        val bpList = bpRepo.getAllBpList()//получили данные

        //ВАРИАНТ 2 БОЛЕЕ КОРОТКИЙ, но менее понятный
        //ключь -это BpEvaluation (оценка), а значение - это счетчик
//        val bpEvaluationCountMap = HashMap<BpEvaluation, Int>()
//            // (это *Табличка) заполняем табличку нулями
//        BpEvaluation.values().forEach {
//            bpEvaluationCountMap[it] = 0
//        }
//        //проходимся по всем данным, сделать оценку
//        bpList.forEach {
//            val evaluation = bpEvaluator.evaluate(it)
//            bpEvaluationCountMap[evaluation] = bpEvaluationCountMap[evaluation]!! +1
//        }
//
//        //наполнили данными
//        val pieEntries = bpEvaluationCountMap.keys.map{
//            PieEntry(bpEvaluationCountMap[it]!!.toFloat())
//        }

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
            BpEvaluation.NORMAL.color,
            BpEvaluation.PRE_HYPERTENSION.color,
            BpEvaluation.HYPERTENSION_1.color,
            BpEvaluation.HYPERTENSION_2.color,
            BpEvaluation.UNKNOWN.color
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