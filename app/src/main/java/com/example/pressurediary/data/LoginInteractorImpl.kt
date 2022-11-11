package com.example.pressurediary.data

import com.example.pressurediary.domain.entities.UserEntity
import com.example.pressurediary.domain.interactors.LoginInteractor
import com.example.pressurediary.domain.repos.BpRepo
import com.example.pressurediary.domain.repos.UserRepo
import com.google.firebase.auth.FirebaseAuth

class LoginInteractorImpl(
    private val userRepo: UserRepo,
    private val bpRepo: BpRepo
) : LoginInteractor {

    private val myAuth: FirebaseAuth = FirebaseAuth.getInstance()

    //для примера создаем пользователя и возвращаем его
//    private val mockUser: UserEntity = UserEntity(
//        name = "mockUser"
//    )

    override fun login(login: String, password: Int, onResult: (Boolean) -> Unit) {
        //проверка пользователя
//        if (login == "mock" && password == 0) {
        //вариант 2 . Если логин пустой тогда не пускаем, иначе будем создавать нового пользователя
        if (login.isBlank()) {
            onResult.invoke(false)
        } else {
//          // сохраняем пользователя который пришел
            val user = UserEntity(id = login, name = login)
            userRepo.addUser(user)
            onResult.invoke(true)
        }
    }

    //работа с разлогированием
    override fun logout() {
        //очищаем кэш (это действие первое)
        bpRepo.clearCache()
        //удаление пользователя (это действие второе)
        userRepo.removeUser()

    }
}