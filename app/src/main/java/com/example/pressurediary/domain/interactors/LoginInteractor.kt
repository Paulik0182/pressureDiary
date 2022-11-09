package com.example.pressurediary.domain.interactors

import com.example.pressurediary.domain.entities.UserEntity

interface LoginInteractor {

    // передаем логин, пароль, результат возвращается
    fun login(login: String, password: Int, onResult: (UserEntity?) -> Unit)
    //метод который возвращает успех или нет (в данном случае, если null то - не успех, если значение есть то ок)


}