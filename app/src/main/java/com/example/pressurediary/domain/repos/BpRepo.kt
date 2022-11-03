package com.example.pressurediary.domain.repos

import com.example.pressurediary.domain.entities.BpEntity

interface BpRepo {
    fun addBp(bpEntity: BpEntity)//добавить
    fun getAllBpList(): List<BpEntity>//получить
    fun getAllBpList(onSuccess: (List<BpEntity>) -> Unit) //получить firebase
    fun removeBp(bpEntity: BpEntity)//удалить
    fun updateBp(changedBp: BpEntity)//обновить
}