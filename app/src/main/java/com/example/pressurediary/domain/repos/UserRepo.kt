package com.example.pressurediary.domain.repos

import com.example.pressurediary.domain.entities.UserEntity

interface UserRepo {
    fun addUser(userEntity: UserEntity)//добавить
    fun getUser(): UserEntity?//получить
    fun removeUser()//удалить
    fun updateUser(changedUser: UserEntity)//обновить
}