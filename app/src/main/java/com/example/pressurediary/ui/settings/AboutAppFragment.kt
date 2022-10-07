package com.example.pressurediary.ui.settings

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import com.example.pressurediary.BuildConfig
import com.example.pressurediary.R

class AboutAppFragment : Fragment(R.layout.fragment_about_app) {

    private lateinit var versionTv: TextView
    private lateinit var versionCodTv: TextView
    private lateinit var aboutAppTv: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        setElementsView()
    }

    private fun initViews(view: View) {
        versionTv = view.findViewById(R.id.version_text_view)
        versionCodTv = view.findViewById(R.id.cod_version_text_view)
        aboutAppTv = view.findViewById(R.id.about_app_text_view)

    }

    @SuppressLint("SetTextI18n")
    private fun setElementsView() {
        versionCodTv.text = "Код версии: " + BuildConfig.VERSION_CODE
        versionTv.text = "Версия: " + BuildConfig.VERSION_NAME
        aboutAppTv.text = "О Приложении\nДневник давления создан " +
                "для самоконтроля артериального давления в течение суток, " +
                "данные понадобятся самому больному и его лечащему врачу."
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
        fun newInstance() = AboutAppFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }
}