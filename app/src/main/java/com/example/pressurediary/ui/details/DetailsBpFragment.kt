package com.example.pressurediary.ui.details

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pressurediary.R
import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.domain.repos.BpRepo
import org.koin.android.ext.android.inject

private const val DETAILS_BP_KEY = "DETAILS_BP_KEY"
private const val DETAILS_BP_ID_KEY = "DETAILS_BP_ID_KEY"

class DetailsBpFragment : Fragment(R.layout.fragment_details_bp) {

    private val bpRepo: BpRepo by inject() //получили через Koin

    private lateinit var bpEntity: MutableList<BpEntity>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        initView(view)
    }

    private fun initView(view: View) {
        TODO("Not yet implemented")
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
        fun newInstance(bpId: Long, bpEntity: BpEntity) = DetailsBpFragment().apply {
            arguments = Bundle().apply {
                putLong(DETAILS_BP_ID_KEY, bpId)
                putParcelable(DETAILS_BP_KEY, bpEntity)
            }
        }
    }
}