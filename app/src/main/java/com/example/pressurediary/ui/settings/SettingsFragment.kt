package com.example.pressurediary.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.pressurediary.R


class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var aboutAppButton: Button
    private lateinit var shareAppButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        aboutAppButton.setOnClickListener{
            getController().openAboutApp()
        }

        shareAppButton.setOnClickListener{
            //TODO
        }
    }

    private fun initViews(view: View) {
        aboutAppButton = view.findViewById(R.id.about_app_button)
        shareAppButton = view.findViewById(R.id.share_app_button)
    }

    interface Controller {
        fun openAboutApp()
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }
}