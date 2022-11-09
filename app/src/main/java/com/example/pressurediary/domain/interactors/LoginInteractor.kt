package com.example.pressurediary.domain.interactors

interface LoginInteractor {

    // передаем логин, пароль, результат возвращается
    fun login(login: String, password: Int, onResult: (Boolean) -> Unit)

    //разлогинемся (выход из системы, приложения)
    fun logout()
}