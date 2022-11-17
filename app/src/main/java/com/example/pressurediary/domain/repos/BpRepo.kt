package com.example.pressurediary.domain.repos

import com.example.pressurediary.domain.entities.BpEntity

interface BpRepo {
    fun addBp(bpEntity: BpEntity)
    fun getAllBpList(): List<BpEntity>
    fun getAllBpList(onSuccess: (List<BpEntity>) -> Unit) // firebase
    fun removeBp(bpEntity: BpEntity)
    fun removeAllBp(onSuccess: (List<BpEntity>) -> Unit)
    fun updateBp(changedBp: BpEntity)

    fun clearCache()
}