package com.example.pressurediary.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.domain.repos.BpRepo
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
class BpRepoImpl: BpRepo {

    private var data: MutableList<BpEntity> = mutableListOf()

    init {
        data.add(BpEntity(1, LocalDate.now(), LocalTime.now(), 140, 75, 55, 2, "Нормально"))
        data.add(BpEntity(2, LocalDate.now(), LocalTime.now(), 150, 80, 70, 1, "Плохо"))
        data.add(BpEntity(3, LocalDate.now(), LocalTime.now(), 120, 95, 75, 3, "Хорошо"))
        data.add(BpEntity(4, LocalDate.now(), LocalTime.now(), 125, 85, 60, 4, "Отлично"))
        data.add(BpEntity(5, LocalDate.now(), LocalTime.now(), 130, 90, 80, 2, "Нормально"))
        data.add(BpEntity(6, LocalDate.now(), LocalTime.now(), 145, 60, 70, 1, "Плохо"))
        data.add(BpEntity(7, LocalDate.now(), LocalTime.now(), 155, 100, 80, 0, "Очень плохо"))
        data.add(BpEntity(8, LocalDate.now(), LocalTime.now(), 140, 75, 55, 2, "Нормально"))
        data.add(BpEntity(9, LocalDate.now(), LocalTime.now(), 150, 80, 70, 1, "Плохо"))
        data.add(BpEntity(10, LocalDate.now(), LocalTime.now(), 120, 95, 75, 3, "Хорошо"))
        data.add(BpEntity(11, LocalDate.now(), LocalTime.now(), 125, 85, 60, 4, "Отлично"))
        data.add(BpEntity(12, LocalDate.now(), LocalTime.now(), 130, 90, 80, 2, "Нормально"))
        data.add(BpEntity(13, LocalDate.now(), LocalTime.now(), 145, 60, 70, 1, "Плохо"))
        data.add(BpEntity(14, LocalDate.now(), LocalTime.now(), 155, 100, 80, 0, "Очень плохо"))
    }

    override fun addParameterDiary(cardioMeasurements: BpEntity) {
        data.add(cardioMeasurements)
    }

    override fun getParameterDiary(): List<BpEntity> {
        return ArrayList(data)
    }

    override fun removeParameterDiary(cardioMeasurements: BpEntity) {
        data.remove(cardioMeasurements)
    }
}