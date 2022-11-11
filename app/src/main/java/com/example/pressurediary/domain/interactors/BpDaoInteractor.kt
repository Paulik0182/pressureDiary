package com.example.pressurediary.domain.interactors

import com.example.pressurediary.domain.repos.BpRepo

interface BpDaoInteractor: BpRepo {

    fun addOnDataChangedListener(listener: Runnable)
    fun removeListener(listener: Runnable)
}