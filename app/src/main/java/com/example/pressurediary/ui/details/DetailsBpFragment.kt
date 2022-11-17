package com.example.pressurediary.ui.details

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pressurediary.R
import com.example.pressurediary.domain.BpEvaluation
import com.example.pressurediary.domain.Emoji
import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.domain.interactors.BpDaoInteractor
import com.example.pressurediary.domain.interactors.BpEvaluator
import com.example.pressurediary.ui.utils.bpDataTimeFormatter
import com.example.pressurediary.ui.utils.getColor
import com.example.pressurediary.ui.utils.toastFragment
import org.koin.android.ext.android.inject
import java.util.*

private const val DETAILS_BP_KEY = "DETAILS_BP_KEY"

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

    private val bpRepo: BpDaoInteractor by inject()
    private val evaluator: BpEvaluator by inject()

    private lateinit var bpEntity: BpEntity

    private lateinit var saveMenuItem: MenuItem
    private lateinit var delMenuItem: MenuItem
    private lateinit var exitMenuItem: MenuItem

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        initView(view)

        bpEntity = requireArguments().getParcelable(DETAILS_BP_KEY)!!
        setBpEntity(bpEntity)

        setupListener()
    }

    private fun setupListener() {
        emojiFatalTv.setOnClickListener {
            deselectEmoji()
            it.setBackgroundResource(Emoji.FATAL.getColor(requireContext()))
            bpEntity.wellBeing = Emoji.FATAL
        }
        emojiBadlyTv.setOnClickListener {
            deselectEmoji()
            it.setBackgroundResource(Emoji.BADLY.getColor(requireContext()))
            bpEntity.wellBeing = Emoji.BADLY
        }
        emojiFineTv.setOnClickListener {
            deselectEmoji()
            it.setBackgroundResource(Emoji.FINE.getColor(requireContext()))
            bpEntity.wellBeing = Emoji.FINE
        }
        emojiWellTv.setOnClickListener {
            deselectEmoji()
            it.setBackgroundResource(Emoji.WELL.getColor(requireContext()))
            bpEntity.wellBeing = Emoji.WELL
        }
        emojiExcellentTv.setOnClickListener {
            deselectEmoji()
            it.setBackgroundResource(Emoji.EXCELLENT.getColor(requireContext()))
            bpEntity.wellBeing = Emoji.EXCELLENT
        }

        when (bpEntity.wellBeing) {
            Emoji.FATAL -> emojiFatalTv.callOnClick()
            Emoji.BADLY -> emojiBadlyTv.callOnClick()
            Emoji.FINE -> emojiFineTv.callOnClick()
            Emoji.WELL -> emojiWellTv.callOnClick()
            Emoji.EXCELLENT -> emojiExcellentTv.callOnClick()
        }
    }

    private fun deselectEmoji() {
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

        val color = evaluator.evaluate(bpEntity).getColor(requireActivity())

        val header = when (evaluator.evaluate(bpEntity)) {
            BpEvaluation.NORMAL -> R.drawable.ic_heat_yellow_24
            BpEvaluation.PRE_HYPERTENSION -> R.drawable.ic_heat_green_24
            BpEvaluation.HYPERTENSION_1 -> R.drawable.ic_heat_red_24
            BpEvaluation.HYPERTENSION_2 -> R.drawable.ic_heat_red_24
            BpEvaluation.UNKNOWN -> R.drawable.ic_heat_red_24
        }

        systolicEt.setTextColor(color)
        diastolicEt.setTextColor(color)
        pulseEt.setTextColor(color)
        conditionTv.setTextColor(color)
        conditionTv.setBackgroundResource(header)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_for_detailed_fragment, menu)
        saveMenuItem = menu.findItem(R.id.save_icon_menu_items)
        delMenuItem = menu.findItem(R.id.delete_icon_menu_items)
        exitMenuItem = menu.findItem(R.id.exit_icon_menu_items)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_icon_menu_items -> {
                val changedBpEntity = bpEntity.copy(
                    systolicLevel = systolicEt.text.toString().toInt(),
                    diastolicLevel = diastolicEt.text.toString().toInt(),
                    pulse = pulseEt.text.toString().toInt(),
                    conditionUser = descriptionEt.text.toString(),
                )

                val bpRepo = bpRepo
                bpRepo.updateBp(changedBpEntity)
                getController().onDataChanged()
                activity?.onBackPressed()

                view?.toastFragment(getString(R.string.save))
                return true
            }
            R.id.exit_icon_menu_items -> {
                activity?.onBackPressed()
            }

            R.id.delete_icon_menu_items -> {
                val bpRepo = bpRepo

                AlertDialog.Builder(requireContext())
                    .setTitle(getText(R.string.want_to_delete))
                    .setPositiveButton(getText(R.string.yes)) { dialogInterface: DialogInterface, i: Int ->
                        bpRepo.removeBp(bpEntity)
                        activity?.onBackPressed()
                        dialogInterface.dismiss()
                    }
                    .setNegativeButton(getText(R.string.yes)) { dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.dismiss()
                    }
                    .show()
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