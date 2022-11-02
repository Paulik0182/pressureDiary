package com.example.pressurediary.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.example.pressurediary.R

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var aboutAppButton: Button
    private lateinit var shareAppButton: Button
    private lateinit var referenceAppButton: Button
    private lateinit var messageTitleTv: TextView
    private lateinit var lightDarkThemeSwitch: SwitchCompat

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        aboutAppButton.setOnClickListener {
            getController().openAboutApp()
        }

        shareAppButton.setOnClickListener {
            val message = messageTitleTv.text.toString()
            sendMessage(message)
        }

        referenceAppButton.setOnClickListener {
            getController().openReferenceApp()
        }

        //установили бигунок в соответствии с темой
        lightDarkThemeSwitch.isChecked =
            AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        //Более котлин вариант
        lightDarkThemeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked)
                    AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }
//        throw RuntimeException("Привет")//принудительное падение
    }

    private fun initViews(view: View) {
        aboutAppButton = view.findViewById(R.id.about_app_button)
        shareAppButton = view.findViewById(R.id.share_app_button)
        referenceAppButton = view.findViewById(R.id.reference_app_button)
        messageTitleTv = view.findViewById(R.id.message_title_text_view)
        lightDarkThemeSwitch = view.findViewById(R.id.switch_themes)
    }

    private fun sendMessage(message: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, message)
        startActivity(intent)
    }

    interface Controller {
        fun openAboutApp()
        fun openReferenceApp()
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