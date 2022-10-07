package com.example.pressurediary.ui.details

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pressurediary.R
import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.domain.repos.BpRepo
import com.example.pressurediary.utils.bpDataTimeFormatter
import org.koin.android.ext.android.inject
import java.util.*

private const val DETAILS_BP_KEY = "DETAILS_BP_KEY"
private const val SYSTOLIC_MAX_KEY = 134
private const val SYSTOLIC_MIN_KEY = 114

class DetailsBpFragment : Fragment(R.layout.fragment_details_bp) {

    private lateinit var titleDataTimeTv: TextView
    private lateinit var systolicEt: EditText
    private lateinit var diastolicEt: EditText
    private lateinit var pulseEt: EditText
    private lateinit var conditionTv: TextView
    private lateinit var emojiFatalTv: TextView
    private lateinit var emojiBadlyTv: TextView
    private lateinit var emojiFineTv: TextView
    private lateinit var emojiWellTv: TextView
    private lateinit var emojiExcellentTv: TextView
    private lateinit var descriptionEt: EditText

    private val bpRepo: BpRepo by inject() //получили через Koin

    private lateinit var bpEntity: BpEntity

    private lateinit var MenuItem: MenuItem

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        initView(view)

        bpEntity = requireArguments().getParcelable(DETAILS_BP_KEY)!!//взяли передоваемое значение
        setBpEntity(bpEntity)//Положили переданное значение в метод

        setupListener()
    }

    private fun setupListener(){
        emojiFatalTv.setOnClickListener{
            deselectEmoji()
            it.setBackgroundColor(Color.YELLOW)
            bpEntity.wellBeing = 1//присваиваем значение састояничя
        }
        emojiBadlyTv.setOnClickListener{
            deselectEmoji()
            it.setBackgroundColor(Color.YELLOW)
            bpEntity.wellBeing = 2
        }
        emojiFineTv.setOnClickListener{
            deselectEmoji()
            it.setBackgroundColor(Color.YELLOW)
            bpEntity.wellBeing = 3
        }
        emojiWellTv.setOnClickListener{
            deselectEmoji()
            it.setBackgroundColor(Color.YELLOW)
            bpEntity.wellBeing = 4
        }
        emojiExcellentTv.setOnClickListener{
            deselectEmoji()
            it.setBackgroundColor(Color.YELLOW)
            bpEntity.wellBeing = 5
        }

        // чтобы на старте проставлялось соответствующее значение
        when(bpEntity.wellBeing){
            1-> emojiFatalTv.callOnClick()
            2-> emojiBadlyTv.callOnClick()
            3-> emojiFineTv.callOnClick()
            4-> emojiWellTv.callOnClick()
            5-> emojiExcellentTv.callOnClick()
        }
    }

    private fun deselectEmoji(){
        emojiFatalTv.setBackgroundColor(Color.TRANSPARENT)
        emojiBadlyTv.setBackgroundColor(Color.TRANSPARENT)
        emojiFineTv.setBackgroundColor(Color.TRANSPARENT)
        emojiWellTv.setBackgroundColor(Color.TRANSPARENT)
        emojiExcellentTv.setBackgroundColor(Color.TRANSPARENT)
    }

    private fun initView(view: View) {
        titleDataTimeTv = view.findViewById(R.id.title_data_time_text_view)
        systolicEt = view.findViewById(R.id.systolic_detail_edit_text)
        diastolicEt = view.findViewById(R.id.diastolic_detail_edit_text)
        pulseEt = view.findViewById(R.id.pulse_detail_edit_text)
        conditionTv = view.findViewById(R.id.condition_text_view)

        emojiFatalTv = view.findViewById(R.id.emoji_fatal_text_view)
        emojiBadlyTv = view.findViewById(R.id.emoji_badly_text_view)
        emojiFineTv = view.findViewById(R.id.emoji_fine_text_view)
        emojiWellTv = view.findViewById(R.id.emoji_well_text_view)
        emojiExcellentTv = view.findViewById(R.id.emoji_excellent_text_view)

        descriptionEt = view.findViewById(R.id.description_edit_text)
    }

    private fun setBpEntity(bpEntity: BpEntity) {
        titleDataTimeTv.text = bpDataTimeFormatter.format(bpEntity.timeInMs)
        systolicEt.setText(bpEntity.systolicLevel.toString())
        diastolicEt.setText(bpEntity.diastolicLevel.toString())
        pulseEt.setText(bpEntity.pulse.toString())
        descriptionEt.setText(bpEntity.conditionUser)

        val systolicTvInt = systolicEt.text.toString().toInt()
        if (systolicTvInt >= SYSTOLIC_MAX_KEY) {
            systolicEt.setTextColor(Color.RED)
            diastolicEt.setTextColor(Color.RED)
            pulseEt.setTextColor(Color.RED)
            conditionTv.setTextColor(Color.RED)
            conditionTv.setBackgroundResource(R.drawable.ic_heat_red_24)
        } else if (systolicTvInt <= SYSTOLIC_MIN_KEY) {
            systolicEt.setTextColor(Color.MAGENTA)
            diastolicEt.setTextColor(Color.MAGENTA)
            pulseEt.setTextColor(Color.MAGENTA)
            conditionTv.setTextColor(Color.MAGENTA)
            conditionTv.setBackgroundResource(R.drawable.ic_heat_yellow_24)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_for_detailed_fragment, menu)
        MenuItem = menu.findItem(R.id.save_icon_menu_items)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_icon_menu_items -> {
                //Второй способ. Делаем копию данных чтобы потом изменить часть данных.
                // Первый способ в предыдущем коммите
                val changedBpEntity = bpEntity.copy(
                    systolicLevel = systolicEt.text.toString().toInt(),
                    diastolicLevel = diastolicEt.text.toString().toInt(),
                    pulse = pulseEt.text.toString().toInt(),
                    conditionUser = descriptionEt.text.toString()
                //В данном коде мы взяли только часть интересующих полей и изменили их,
                    // остальные поля остались прежними
                )

                val bpRepo = bpRepo
                bpRepo.updateBp(changedBpEntity)//добавили новые данные
                getController().onDataChanged()//обновили данные

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
        fun onDataChanged()
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {
        //начало записи гласит о том, что если уже существует, то возмем ее, если нет то создадим новую
        @JvmStatic
        fun newInstance(
            bpEntity: BpEntity = BpEntity(
                timeInMs = Calendar.getInstance().timeInMillis
            )
        ) = DetailsBpFragment().apply {
            arguments = Bundle().apply {
                putParcelable(DETAILS_BP_KEY, bpEntity)
            }
        }
    }
}