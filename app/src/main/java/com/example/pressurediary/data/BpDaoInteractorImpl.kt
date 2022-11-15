package com.example.pressurediary.data

import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.domain.interactors.BpDaoInteractor
import com.example.pressurediary.domain.repos.BpRepo

class BpDaoInteractorImpl(
    private val bpRepo: BpRepo
): BpDaoInteractor {

    private val listeners: MutableSet<Runnable> = mutableSetOf()

    override fun addOnDataChangedListener(listener: Runnable) {
        listeners.add(listener)
    }

    override fun removeListener(listener: Runnable) {
        listeners.remove(listener)
    }

    override fun addBp(bpEntity: BpEntity) {
        bpRepo.addBp(bpEntity)
        notifyListener()
    }

    override fun getAllBpList(): List<BpEntity> = bpRepo.getAllBpList()
    override fun getAllBpList(onSuccess: (List<BpEntity>) -> Unit) {
        bpRepo.getAllBpList(onSuccess)
    }

    override fun removeBp(bpEntity: BpEntity) {
        bpRepo.removeBp(bpEntity)
        notifyListener()
    }

    override fun updateBp(changedBp: BpEntity) {
        bpRepo.updateBp(changedBp)
        notifyListener()
    }

    override fun clearCache() {
        bpRepo.clearCache()
        notifyListener()
    }

    private fun notifyListener() {
        listeners.forEach {
            it.run()
        }
    }
}