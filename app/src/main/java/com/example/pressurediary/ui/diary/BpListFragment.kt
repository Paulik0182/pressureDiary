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

private const val ARG_PARAM1 = "param1"

class BpListFragment : Fragment() {

    private lateinit var recordsTv: TextView

    private val app: App by lazy { requireActivity().application as App }

    private var param1: String? = null

    private lateinit var adapter: BpListAdapter
    private val listener = { bpEntity: BpEntity ->
        fillView(bpEntity)
    }

    private lateinit var bpRepo: BpRepo
    private lateinit var recyclerView: RecyclerView

    private lateinit var bpList: MutableList<BpEntity>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bp_list, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)

        bpRepo = app.bpRepo

        adapter.setData(bpRepo.getParameterDiary())
    }

    private fun initView(view: View) {
        recordsTv = view.findViewById(R.id.records_text_view)
        recyclerView = view.findViewById(R.id.cardio_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = BpListAdapter(emptyList(), listener)
        recyclerView.adapter = adapter
    }

    private fun fillView(bpEntity: BpEntity) {
        recordsTv.text = "Записи"
        adapter.setData(bpList)

    }

    interface Controller {
        // TODO
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            BpListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}