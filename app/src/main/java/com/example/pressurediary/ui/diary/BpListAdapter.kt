package com.example.pressurediary.ui.diary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pressurediary.R
import com.example.pressurediary.domain.entities.BpEntity
import java.lang.IllegalStateException
import java.util.*


//Для то чтобы наследоватся от двух ViewHolder. По факту адаптер будет работать с двумя ViewHolder
abstract class BaseBpViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

// Константы для того чтобы различать ViewHolder
private const val BP_HOLDER_TYPE = 0
private const val HEADER_HOLDER_TYPE = 1

//Создали класс для промежуточных данных
data class BaseBpAdapterItem(
    val viewHolderType: Int = -1,//содержит тип viewHolder
    val data: Long? = null, // если 1 тип viewHolder то содержить дату
    val bpEntity: BpEntity? = null// если 0 тип viewHolder то содержить BpEntity
)

class BpListAdapter(
    private var listener: (BpEntity) -> Unit
) : RecyclerView.Adapter<BaseBpViewHolder>() {

    //Создали класс для промежуточных данных
    private var bpListWithHeaders: MutableList<BaseBpAdapterItem> = mutableListOf()

    //для проверки bpEntity
    private var lastKnownBp: BpEntity? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<BpEntity>) {
        // Делаем обратную сортировку данных на конкретном экране
        // проходим по всему массиву данных и заполняем bpListWithHeaders (элементами список)
        data
            .sortedByDescending {
                it.timeInMs
            }
            .forEach {
                val lastKnownBpCalendar = Calendar.getInstance()//создали календарь
                lastKnownBpCalendar.timeInMillis = lastKnownBp?.timeInMs ?: 0 // положили нужное время
                val lastDay = lastKnownBpCalendar.get(Calendar.DAY_OF_YEAR)// достали номер дня в году

                // тоже самое повторяем новое
                val newKnownBpCalendar = Calendar.getInstance()//создали календарь
                newKnownBpCalendar.timeInMillis = it.timeInMs ?: 0 // положили нужное время
                val newDay = newKnownBpCalendar.get(Calendar.DAY_OF_YEAR)// достали номер дня в году

                val neadToShowHeader = lastKnownBp == null || lastDay != newDay
                //заполняем элементами viewHolder
                if (neadToShowHeader){
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
        //Проверяется какой тип холдера приходит, тот и будет отрисоват (будут созданы соответствующие классы
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

    //переопределяем метод который и будет решать какой тип ViewHolder
    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewHolderType
    }

    override fun onBindViewHolder(holder: BaseBpViewHolder, position: Int) {
        // Определяем тип bind. Устанавливаем соответствующие значения
        val item = getItem((position))
//        when (item.viewHolderType){
        when (getItemViewType(position)){
            BP_HOLDER_TYPE -> (holder as BpListViewHolder).bind(item.bpEntity!!)
            HEADER_HOLDER_TYPE -> (holder as BpDayHeaderViewHolder).bind(item.data!!)
            else -> throw IllegalStateException("Нет такого холдера")
        }
    }

    private fun getItem(position: Int): BaseBpAdapterItem = bpListWithHeaders[position]

    override fun getItemCount(): Int = bpListWithHeaders.size
}
