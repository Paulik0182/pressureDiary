package com.example.pressurediary.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.pressurediary.domain.entities.DiaryEntity
import com.example.pressurediary.domain.repos.DiaryRepo
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
class DiaryRepoImpl: DiaryRepo {

    private var data: MutableList<DiaryEntity> = mutableListOf()

//    init {
//        data.add(DiaryEntity(1, LocalDate.now(), LocalTime.now(), 140, 75, 55, 2, "Нормально"))
//        data.add(DiaryEntity(2, LocalDate.now(), LocalTime.now(), 150, 80, 70, 1, "Плохо"))
//        data.add(DiaryEntity(3, LocalDate.now(), LocalTime.now(), 120, 95, 75, 3, "Хорошо"))
//        data.add(DiaryEntity(4, LocalDate.now(), LocalTime.now(), 125, 85, 60, 4, "Отлично"))
//        data.add(DiaryEntity(5, LocalDate.now(), LocalTime.now(), 130, 90, 80, 2, "Нормально"))
//        data.add(DiaryEntity(6, LocalDate.now(), LocalTime.now(), 145, 60, 70, 1, "Плохо"))
//        data.add(DiaryEntity(7, LocalDate.now(), LocalTime.now(), 155, 100, 80, 0, "Очень плохо"))
//    }

    override fun addParameterDiary(cardioMeasurements: DiaryEntity) {
        data.add(cardioMeasurements)
    }

    override fun getParameterDiary(): List<DiaryEntity> {
        return ArrayList(data)
    }

    override fun removeParameterDiary(cardioMeasurements: DiaryEntity) {
        data.remove(cardioMeasurements)
    }


    init {
        addParameterDiary(
            DiaryEntity(
                1, LocalDate.now(), LocalTime.now(), 130, 90, 65, 2, "Нормально"
            )
        )
        addParameterDiary(
            DiaryEntity(
                2, LocalDate.now(), LocalTime.now(), 140, 75, 55, 2, "Нормально"
            )
        )
        addParameterDiary(
            DiaryEntity(
                3, LocalDate.now(), LocalTime.now(), 150, 80, 70, 1, "Плохо"
            )
        )
        addParameterDiary(
            DiaryEntity(
                4, LocalDate.now(), LocalTime.now(), 120, 95, 75, 3, "Хорошо"
            )
        )
        addParameterDiary(
            DiaryEntity(
                5, LocalDate.now(), LocalTime.now(), 125, 85, 60, 4, "Отлично"
            )
        )
        addParameterDiary(
            DiaryEntity(
                6, LocalDate.now(), LocalTime.now(), 130, 90, 80, 2, "Нормально"
            )
        )
        addParameterDiary(
            DiaryEntity(
                7, LocalDate.now(), LocalTime.now(), 145, 60, 70, 1, "Плохо"
            )
        )
        addParameterDiary(
            DiaryEntity(
                8, LocalDate.now(), LocalTime.now(), 155, 100, 80, 0, "Очень плохо"
            )
        )
    }
}