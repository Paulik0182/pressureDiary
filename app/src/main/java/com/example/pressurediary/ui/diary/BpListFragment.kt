package com.example.pressurediary.ui.diary

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pressurediary.R
import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.domain.interactors.BpDaoInteractor
import com.example.pressurediary.domain.interactors.BpEvaluator
import org.koin.android.ext.android.inject

private const val BP_LIST_KEY = "BP_LIST_KEY"

class BpListFragment : Fragment(R.layout.fragment_bp_list) {

    private lateinit var recordsTv: TextView
    private lateinit var fab: View

    private val evaluator: BpEvaluator by inject()

    private lateinit var adapter: BpListAdapter

    private val bpRepo: BpDaoInteractor by inject() //получили через Koin
    private lateinit var recyclerView: RecyclerView

    private lateinit var bpList: MutableList<BpEntity>

    //подсчет количество записей в БД
    private var records: Int = 0

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)

        //подсчет количество записей в БД (подсчет на старте)
        records = bpRepo.getAllBpList().size
        recordsTv.text = "Записи: $records"

        adapter.setData(bpRepo.getAllBpList())
        adapter.setOnItemClickListener {
            getController().openDetailsBp(it)
        }

        fab.setOnClickListener {
            getController().openDetailsBp(null)
        }

        // подписка на изменения
        bpRepo.addOnDataChangedListener(listenerDataChange)
    }

    private val listenerDataChange = Runnable {
        onDataChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bpRepo.removeListener(listenerDataChange)
    }

    private fun initView(view: View) {
        fab = view.findViewById(R.id.fab)
        recordsTv = view.findViewById(R.id.records_text_view)
        recyclerView = view.findViewById(R.id.cardio_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = BpListAdapter(evaluator)
        recyclerView.adapter = adapter
    }

    interface Controller {
        fun openDetailsBp(bpEntity: BpEntity?)
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    @SuppressLint("SetTextI18n")
    private fun onDataChanged() {
        //подсчет количество записей в БД (каждый раз как мы добавляем данные)
        records = bpRepo.getAllBpList().size
        recordsTv.text = "Записи: $records"
        adapter.setData(bpRepo.getAllBpList())//Если изменились данные, вставляем их в адаптер
    }

    companion object {
        @JvmStatic
        fun newInstance() = BpListFragment().apply {
            arguments = Bundle().apply {
                putString(BP_LIST_KEY, "BP_LIST_KEY")
            }
        }
    }
}