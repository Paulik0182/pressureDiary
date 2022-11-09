package com.example.pressurediary.data

import com.example.pressurediary.domain.entities.UserEntity
import com.example.pressurediary.domain.repos.UserRepo

class UserRepoImpl : UserRepo {

    private var user: UserEntity? = null

    override fun addUser(userEntity: UserEntity) {
        user = userEntity
    }

    override fun getUser(): UserEntity? = user


    override fun removeUser() {
        user = null
    }

    override fun updateUser(changedUser: UserEntity) {
        user = changedUser
    }
}