package com.example.pressurediary.domain.interactors

import com.example.pressurediary.domain.entities.UserEntity

interface LoginInteractor {

    fun getUser(): UserEntity?//получить

    // передаем логин, пароль, результат возвращается
    fun login(login: String, password: Int, onResult: (Boolean) -> Unit)

    //регистрация
    fun register(login: String, password: Int, onResult: (Boolean) -> Unit)


    //разлогинемся (выход из системы, приложения)
    fun logout()
}