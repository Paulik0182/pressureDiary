package com.example.pressurediary.ui.advice

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pressurediary.R

class AdviceFragment : Fragment(R.layout.fragment_advice) {

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
        fun newInstance() = AdviceFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }
}