package com.example.pressurediary.ui.settings.reference

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pressurediary.R
import com.example.pressurediary.domain.repos.ReferenceRepo
import org.koin.android.ext.android.inject

private const val HTTP_MP_ANDROID_CHART_KEY = "http://github.com/PhilJay/MPAndroidChart"

class ReferenceFragment : Fragment(R.layout.fragment_reference) {

    private lateinit var exitButton: Button

    private lateinit var adapter: ReferenceAdapter
    private lateinit var recyclerView: RecyclerView
    private val referenceRepo: ReferenceRepo by inject() //получили через Koin

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)

        adapter.setData(referenceRepo.getReference())

        exitButton.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun initView(view: View) {
        exitButton = view.findViewById(R.id.exit_button)

        recyclerView = view.findViewById(R.id.references_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setOnClickListener {

        }
        adapter = ReferenceAdapter(
            openLink = {
                sendBrowser()
            })
        recyclerView.adapter = adapter
    }

    private fun sendBrowser() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(HTTP_MP_ANDROID_CHART_KEY)
        )
        startActivity(intent)
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
        fun newInstance() = ReferenceFragment()
    }
}