package com.example.pressurediary.ui.chart

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pressurediary.R

class ChartFragment : Fragment(R.layout.fragment_chart) {

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