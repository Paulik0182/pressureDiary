package com.example.pressurediary

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.pressurediary.data.BpRepoImpl
import com.example.pressurediary.di.appModule
import com.example.pressurediary.domain.repos.BpRepo
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Здесь создаем репозиторий. Репо должна быть одна, а не создаватся каждый раз в каждом фрагменте.
 * этот класс для того чтобы воспользоватся application.
 * Необходимо прописать в манифесте данный класс
 * android:name=".App"
 *
 * здесь необходимо инициализировать KOIN. здесь будет начало работы приложения. стартовая точка приложения onCreate
 */

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        //подключение Koin
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}