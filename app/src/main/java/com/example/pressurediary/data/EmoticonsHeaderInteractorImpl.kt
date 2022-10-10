package com.example.pressurediary.data

import android.graphics.Color
import com.example.pressurediary.domain.Emoji
import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.domain.interactors.EmoticonsHeaderInteractor
import java.util.*
import kotlin.collections.ArrayList

private const val SYSTOLIC_MAX_KEY = 134
private const val SYSTOLIC_MIN_KEY = 114

class EmoticonsHeaderInteractorImpl(
) : EmoticonsHeaderInteractor {

    private var idCounter: Long = 0L

    private var data: MutableList<BpEntity> = mutableListOf()

    override fun addBp(bpEntity: BpEntity) {
        bpEntity.id = ++idCounter//добавляем к id по одному значению. Сначало плюс один, а потом читаем значение
        data.add(bpEntity)
//        colorData(bpEntity)
    }

    override fun getBpList(): List<BpEntity> {
//       colorData(bpEntity = BpEntity())
        return ArrayList(data)
    }

    override fun removeBp(
        bpEntity: BpEntity
    ) {
        data.remove(bpEntity)
//        colorData(bpEntity)
    }

    override fun updateBp(
        changedBp: BpEntity
    ) {
        val id = changedBp.id//изменяемая запись (id записи)

        //ищим старую заметку
        var oldBpList: BpEntity? = null
        for (i in data.indices) {
            if (data[i].id == id) {//ищем нужную запись (id)
                oldBpList = data[i]//получаем нужный элемент, записываем его в переменную
                break
            }
        }
        if (oldBpList == null) {
            addBp(changedBp)//если не нашли заметку, то добавляем ее
        } else {
            //если нашли заметку, то меняем ее
            oldBpList.timeInMs = changedBp.timeInMs
            oldBpList.systolicLevel = changedBp.systolicLevel
            oldBpList.diastolicLevel = changedBp.diastolicLevel
            oldBpList.pulse = changedBp.pulse
            oldBpList.conditionUser = changedBp.conditionUser
        }
//        colorData(changedBp)

    }

    private fun colorData(bpEntity: BpEntity){
        val color = bpEntity
        if (bpEntity.systolicLevel >= SYSTOLIC_MAX_KEY){
            color.systolicLevel = Color.RED
            color.diastolicLevel = Color.RED
            color.pulse = Color.RED
            Color.RED.also { color.timeInMs }
        }else if (bpEntity.systolicLevel <= SYSTOLIC_MIN_KEY){
            color.systolicLevel = Color.MAGENTA
            color.diastolicLevel = Color.MAGENTA
            color.pulse = Color.MAGENTA
            Color.MAGENTA.also { color.timeInMs }
        }
    }

    init {
        data.add(BpEntity(1, Calendar.getInstance().timeInMillis,140, 75, 55, Emoji.FINE, "Нормально"))
        data.add(BpEntity(2, Calendar.getInstance().timeInMillis + 10,150, 80, 70, Emoji.BADLY, "Плохо"))
        data.add(BpEntity(3, Calendar.getInstance().timeInMillis + 200000000,120, 95, 75,
            Emoji.WELL, "Хорошо"))
        data.add(BpEntity(4, Calendar.getInstance().timeInMillis + 30,125, 85, 60,
            Emoji.EXCELLENT, "Отлично"))
        data.add(BpEntity(5, Calendar.getInstance().timeInMillis + 400000000,130, 90, 80,
            Emoji.FINE, "Нормально"))
        data.add(BpEntity(6, Calendar.getInstance().timeInMillis + 50,145, 60, 70, Emoji.BADLY, "Плохо"))
        data.add(BpEntity(7, Calendar.getInstance().timeInMillis + 60,155, 100, 80, Emoji.FATAL
            , "Очень плохо"))
        data.add(BpEntity(8, Calendar.getInstance().timeInMillis + 700000000,140, 75, 55,
            Emoji.FINE, "Нормально"))
        data.add(BpEntity(9, Calendar.getInstance().timeInMillis + 80,150, 80, 70, Emoji.BADLY, "Плохо"))
        data.add(BpEntity(10, Calendar.getInstance().timeInMillis + 90, 120, 95, 75, Emoji.WELL, "Хорошо"))
        data.add(BpEntity(11, Calendar.getInstance().timeInMillis + 100, 125, 85, 60,
            Emoji.EXCELLENT, "Отлично"))
        data.add(BpEntity(12, Calendar.getInstance().timeInMillis + 110, 130, 90, 80, Emoji.FINE
            , "Нормально"))
        data.add(BpEntity(13, Calendar.getInstance().timeInMillis + 120, 145, 60, 70, Emoji.BADLY, "Плохо"))
        data.add(BpEntity(14,
            Calendar.getInstance().timeInMillis + 130, 155, 100, 80, Emoji.FATAL, "Очень плохо"))
        data.add(BpEntity(15, Calendar.getInstance().timeInMillis + 140, 110, 60, 55, Emoji.FATAL, "Очень плохо"))
        data.add(BpEntity(16, Calendar.getInstance().timeInMillis + 150, 100, 55, 60, Emoji.FATAL, "Очень плохо"))
        idCounter = 16
    }
}