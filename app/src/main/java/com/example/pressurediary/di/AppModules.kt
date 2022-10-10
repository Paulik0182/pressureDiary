package com.example.pressurediary.di

import com.example.pressurediary.data.BpRepoImpl
import com.example.pressurediary.data.EmoticonsHeaderInteractorImpl
import com.example.pressurediary.domain.interactors.EmoticonsHeaderInteractor
import com.example.pressurediary.domain.repos.BpRepo
import org.koin.dsl.module

val appModule = module {

    single<BpRepo> { BpRepoImpl() }
    single<EmoticonsHeaderInteractor> { EmoticonsHeaderInteractorImpl() }
}