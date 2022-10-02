package com.example.pressurediary.domain.repos

import com.example.pressurediary.domain.entities.DiaryEntity

interface DiaryRepo {
    fun addParameterDiary(cardioMeasurements: DiaryEntity)//добавить
    fun getParameterDiary(): List<DiaryEntity>//получить
    fun removeParameterDiary(cardioMeasurements: DiaryEntity)//удалить
}