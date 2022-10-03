package com.example.pressurediary.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.domain.repos.BpRepo
import java.util.*
import kotlin.collections.ArrayList

@RequiresApi(Build.VERSION_CODES.O)
class BpRepoImpl: BpRepo {

    private var data: MutableList<BpEntity> = mutableListOf()

    init {
        data.add(BpEntity(1, Calendar.getInstance().timeInMillis,140, 75, 55, 2, "Нормально"))
        data.add(BpEntity(2, Calendar.getInstance().timeInMillis,150, 80, 70, 1, "Плохо"))
        data.add(BpEntity(3, Calendar.getInstance().timeInMillis,120, 95, 75, 3, "Хорошо"))
        data.add(BpEntity(4, Calendar.getInstance().timeInMillis,125, 85, 60, 4, "Отлично"))
        data.add(BpEntity(5, Calendar.getInstance().timeInMillis,130, 90, 80, 2, "Нормально"))
        data.add(BpEntity(6, Calendar.getInstance().timeInMillis,145, 60, 70, 1, "Плохо"))
        data.add(BpEntity(7, Calendar.getInstance().timeInMillis,155, 100, 80, 0, "Очень плохо"))
        data.add(BpEntity(8, Calendar.getInstance().timeInMillis,140, 75, 55, 2, "Нормально"))
        data.add(BpEntity(9, Calendar.getInstance().timeInMillis,150, 80, 70, 1, "Плохо"))
        data.add(BpEntity(10,Calendar.getInstance().timeInMillis, 120, 95, 75, 3, "Хорошо"))
        data.add(BpEntity(11,Calendar.getInstance().timeInMillis, 125, 85, 60, 4, "Отлично"))
        data.add(BpEntity(12,Calendar.getInstance().timeInMillis, 130, 90, 80, 2, "Нормально"))
        data.add(BpEntity(13,Calendar.getInstance().timeInMillis, 145, 60, 70, 1, "Плохо"))
        data.add(BpEntity(14,Calendar.getInstance().timeInMillis, 155, 100, 80, 0, "Очень плохо"))
    }

    override fun addBp(bpEntity: BpEntity) {
        data.add(bpEntity)
    }

    override fun getAllBpList(): List<BpEntity> {
        return ArrayList(data)
    }

    override fun removeBp(bpEntity: BpEntity) {
        data.remove(bpEntity)
    }
}