package com.example.pressurediary.di

import com.example.pressurediary.data.*
import com.example.pressurediary.domain.interactors.BpDaoInteractor
import com.example.pressurediary.domain.interactors.BpEvaluator
import com.example.pressurediary.domain.interactors.LoginInteractor
import com.example.pressurediary.domain.repos.BpRepo
import com.example.pressurediary.domain.repos.ReferenceRepo
import com.example.pressurediary.domain.repos.UserRepo
import org.koin.dsl.module

val appModule = module {

    single<UserRepo> { UserRepoImpl() }
    single<BpRepo> { FirebaseBpRepoImpl(get()) }
    single<BpDaoInteractor> { BpDaoInteractorImpl(get()) }
    single<BpEvaluator> { BpEvaluatorImpl() }
    single<ReferenceRepo> { ReferenceRepoImpl() }
    single<LoginInteractor> { LoginInteractorImpl(get(), get<BpDaoInteractor>()) }
    //get<BpDaoInteractor>()) - потаму что у него ест правильный метод КЭШ который потом всех уведомит
}