package com.example.pressurediary.di

import com.example.pressurediary.data.BpDaoInteractorImpl
import com.example.pressurediary.data.BpEvaluatorImpl
import com.example.pressurediary.data.FirebaseBpRepoImpl
import com.example.pressurediary.data.ReferenceRepoImpl
import com.example.pressurediary.domain.interactors.BpDaoInteractor
import com.example.pressurediary.domain.interactors.BpEvaluator
import com.example.pressurediary.domain.repos.BpRepo
import com.example.pressurediary.domain.repos.ReferenceRepo
import org.koin.dsl.module

val appModule = module {

//    single<BpRepo> { BpRepoImpl() }
    single<BpRepo> { FirebaseBpRepoImpl() }
    single<BpDaoInteractor> { BpDaoInteractorImpl(get()) }
    single<BpEvaluator> { BpEvaluatorImpl() }
    single<ReferenceRepo> { ReferenceRepoImpl() }
}