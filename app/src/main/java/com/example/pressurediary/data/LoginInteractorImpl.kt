package com.example.pressurediary.data

import com.example.pressurediary.domain.entities.UserEntity
import com.example.pressurediary.domain.interactors.LoginInteractor

class LoginInteractorImpl : LoginInteractor {

    //для примера создаем пользователя и возвращаем его
//    private val mockUser: UserEntity = UserEntity(
//        name = "mockUser"
//    )

    override fun login(login: String, password: Int, onResult: (UserEntity?) -> Unit) {
        //проверка пользователя
//        if (login == "mock" && password == 0) {
        //вариант 2 . Если логин пустой тогда не пускаем, иначе будем создавать нового пользователя
        if (login.isBlank()) {
            onResult.invoke(null)
        } else {
//            onResult.invoke(null)
            onResult.invoke(UserEntity(id = login, name = login))
        }
    }
}