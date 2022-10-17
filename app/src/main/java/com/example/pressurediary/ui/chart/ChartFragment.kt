package com.example.pressurediary.ui.chart

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.pressurediary.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class ChartFragment : Fragment(R.layout.fragment_chart) {

    private lateinit var pieChart: PieChart

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pieChart = view.findViewById(R.id.pie_chart)

        //Заводим некий лист входных псевдо данных
        val pieEntries = listOf<PieEntry>(
            PieEntry(100f, "Высокое"),
            PieEntry(50f, "Низкое"),
            PieEntry(10f, "Нормальное")
        )

        //получаем количество значений и название
        val pieDataSet = PieDataSet(pieEntries, "Давление")

            //Раскрашиваем значения. ВНИМАНИЕ на написание colors
        pieDataSet.colors = listOf(Color.CYAN, Color.RED, Color.GREEN)

        //заводим сущьность pie data
        val pieData = PieData(pieDataSet)

        //принимает на вход некую сущьность (данные)
        pieChart.data = pieData

            //регулирования радиуса отверстия
//        pieChart.holeRadius = 0f
        pieChart.isDrawHoleEnabled = false // Убрать отверстие
//        pieChart.setCenterTextColor(Color.BLACK)//цвет значений (не названий)
        pieChart.setEntryLabelColor(Color.BLACK)//цвет всех надписей
        pieChart.setEntryLabelTextSize(22f)//размер только надписей

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