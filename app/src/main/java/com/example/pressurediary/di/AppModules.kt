package com.example.pressurediary.di

import com.example.pressurediary.data.BpDaoInteractorImpl
import com.example.pressurediary.data.BpRepoImpl
import com.example.pressurediary.data.BpEvaluatorImpl
import com.example.pressurediary.domain.interactors.BpDaoInteractor
import com.example.pressurediary.domain.interactors.BpEvaluator
import com.example.pressurediary.domain.repos.BpRepo
import org.koin.dsl.module

val appModule = module {

    single<BpRepo> { BpRepoImpl() }
    single<BpDaoInteractor> { BpDaoInteractorImpl(get()) }
    single<BpEvaluator> { BpEvaluatorImpl() }
}