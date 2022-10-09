package com.example.pressurediary.domain.interactor

import com.example.pressurediary.domain.entities.BpEntity

/**
 * Для преобразования данных, для подписки на изменения
 */

interface EmoticonsHeaderInteractor {
    fun addBp(bpEntity: BpEntity)//добавить
    fun getBpList(): List<BpEntity>//получить
    fun removeBp(bpEntity: BpEntity)//удалить
    fun updateBp(changedBp: BpEntity)//обновить

}