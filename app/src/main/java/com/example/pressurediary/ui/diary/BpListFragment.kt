package com.example.pressurediary.ui.diary

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pressurediary.App
import com.example.pressurediary.R
import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.domain.repos.BpRepo
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class BpListFragment : Fragment(R.layout.fragment_bp_list) {

    private lateinit var recordsTv: TextView
    private lateinit var fab: View

    private lateinit var adapter: BpListAdapter
    private val listener = { bpEntity: BpEntity ->
        fillView(bpEntity)
    }

    private val bpRepo: BpRepo by inject() //получили через Koin
    private lateinit var recyclerView: RecyclerView

    private lateinit var bpList: MutableList<BpEntity>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)

        adapter.setData(bpRepo.getAllBpList())
        adapter.setOnItemClickListener {
            getController().openDetailsBp(it.id, it)
        }

        recordsTv.text = "Записи:"

        fab.setOnClickListener {
            getController().addDetailBp()
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }
    }

    private fun initView(view: View) {
        fab = view.findViewById(R.id.fab)
        recordsTv = view.findViewById(R.id.records_text_view)
        recyclerView = view.findViewById(R.id.cardio_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = BpListAdapter(listener)
        recyclerView.adapter = adapter
    }

    private fun fillView(bpEntity: BpEntity) {
        recordsTv.text = "Записи"
        adapter.setData(bpList)

    }

    interface Controller {
        fun openDetailsBp(bpId: Long, bpEntity: BpEntity)
        fun addDetailBp()
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }
}