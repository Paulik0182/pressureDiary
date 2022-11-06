package com.example.pressurediary.domain.repos

import com.example.pressurediary.domain.entities.UserEntity

interface UserRepo {
    fun addUser(userEntity: UserEntity)//добавить
    fun getUser(): List<UserEntity>//получить
    fun getAllUser(onSuccess: (List<UserEntity>) -> Unit) //получить firebase
    fun removeUser(userEntity: UserEntity)//удалить
    fun updateUser(changedUser: UserEntity)//обновить
}