package com.example.pressurediary.ui.diary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pressurediary.R
import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.domain.interactors.BpEvaluator
import java.util.*


//Для то чтобы наследоватся от двух ViewHolder. По факту адаптер будет работать с двумя ViewHolder
abstract class BaseBpViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

private const val BP_HOLDER_TYPE = 0
private const val HEADER_HOLDER_TYPE = 1

//Создали класс для промежуточных данных
data class BaseBpAdapterItem(
    val viewHolderType: Int = -1,
    val data: Long? = null,
    val bpEntity: BpEntity? = null
)

class BpListAdapter(
    private var evaluator: BpEvaluator,
    private var listener: (BpEntity) -> Unit = {}
) : RecyclerView.Adapter<BaseBpViewHolder>() {

    private var bpListWithHeaders: MutableList<BaseBpAdapterItem> = mutableListOf()

    private var lastKnownBp: BpEntity? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<BpEntity>) {
        bpListWithHeaders.clear()
        lastKnownBp = null
        data
            .sortedByDescending {
                it.timeInMs
            }
            .forEach {
                val lastKnownBpCalendar = Calendar.getInstance()
                lastKnownBpCalendar.timeInMillis =
                    lastKnownBp?.timeInMs ?: 0
                val lastDay =
                    lastKnownBpCalendar.get(Calendar.DAY_OF_YEAR)

                // тоже самое повторяем новое
                val newKnownBpCalendar = Calendar.getInstance()
                newKnownBpCalendar.timeInMillis = it.timeInMs ?: 0
                val newDay = newKnownBpCalendar.get(Calendar.DAY_OF_YEAR)

                val needToShowHeader = lastKnownBp == null || lastDay != newDay
                if (needToShowHeader) {
                    bpListWithHeaders.add(BaseBpAdapterItem(HEADER_HOLDER_TYPE, data = it.timeInMs))
                }
                bpListWithHeaders.add(BaseBpAdapterItem(BP_HOLDER_TYPE, bpEntity = it))

                lastKnownBp = it
            }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (BpEntity) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBpViewHolder =
        when (viewType) {
            BP_HOLDER_TYPE -> BpListViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_bp, parent, false), listener
            )
            HEADER_HOLDER_TYPE -> BpDayHeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_bp_header, parent, false)
            )
            else -> throw IllegalStateException("Нет такого холдера")
        }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewHolderType
    }

    override fun onBindViewHolder(holder: BaseBpViewHolder, position: Int) {
        val item = getItem((position))
        when (getItemViewType(position)) {
            BP_HOLDER_TYPE -> (holder as BpListViewHolder).bind(
                item.bpEntity!!,
                evaluator.evaluate(item.bpEntity)
            )
            HEADER_HOLDER_TYPE -> (holder as BpDayHeaderViewHolder).bind(item.data!!)
            else -> throw IllegalStateException("Нет такого холдера")
        }
    }

    private fun getItem(position: Int): BaseBpAdapterItem = bpListWithHeaders[position]

    override fun getItemCount(): Int = bpListWithHeaders.size
}
