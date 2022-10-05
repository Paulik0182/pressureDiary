package com.example.pressurediary.ui.details

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pressurediary.R
import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.domain.repos.BpRepo
import org.koin.android.ext.android.inject

private const val DETAILS_BP_KEY = "DETAILS_BP_KEY"
private const val DETAILS_BP_ID_KEY = "DETAILS_BP_ID_KEY"

class DetailsBpFragment : Fragment(R.layout.fragment_details_bp) {



    private val bpRepo: BpRepo by inject() //получили через Koin

    private lateinit var bpEntity: MutableList<BpEntity>

    private lateinit var MenuItem: MenuItem

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

//        initView(view)
    }

    private fun initView(view: View) {
        TODO("Not yet implemented")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_for_detailed_fragment, menu)
        MenuItem = menu.findItem(R.id.save_icon_menu_items)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.save_icon_menu_items -> {
                        Toast.makeText(
                            requireContext(),
                            "Сохнанить",
                            Toast.LENGTH_SHORT
                        ).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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

        @JvmStatic
        fun newAddInstance() = DetailsBpFragment().apply {
            arguments = Bundle().apply {
                putString(DETAILS_BP_ID_KEY, "addDetailsBp")
            }
        }
    }
}