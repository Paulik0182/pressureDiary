package com.example.pressurediary.domain.repos

import com.example.pressurediary.domain.entities.BpEntity

interface BpRepo {
    fun addParameterDiary(cardioMeasurements: BpEntity)//добавить
    fun getParameterDiary(): List<BpEntity>//получить
    fun removeParameterDiary(cardioMeasurements: BpEntity)//удалить
}