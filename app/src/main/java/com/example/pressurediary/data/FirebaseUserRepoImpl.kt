package com.example.pressurediary.data

import com.example.pressurediary.domain.entities.UserEntity
import com.example.pressurediary.domain.repos.UserRepo
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseUserRepoImpl : UserRepo {

    private var data: List<UserEntity> = emptyList()

    //ссылка на бд. reference - это ссылка на значение
    private val database by lazy {
        Firebase.database(DATABASE_URL_KEY).apply {
            setPersistenceEnabled(true) //для работы без интернета
            reference.keepSynced(true) //для синхранизации данных (кешируем данные)
        }
    }

    override fun getAllUser(onSuccess: (List<UserEntity>) -> Unit) {
        database.reference.keepSynced(true)//для синхранизации данных (кешируем данные)

        database.reference.get()
            .addOnSuccessListener {
                val pressure: MutableList<UserEntity> = mutableListOf()// собираем колекцию
                //ищем все записи (snapshot)
                it.children.forEach { snapshot ->
                    //обработка исключения. Если есть соответствующие данные то обрабатываем,
                    // если данные не соответствуют то ничего с ними не делаем
                    try {
                        //Парсим значения. Если значение не null то добавляем в колекцию (lessons.add(it) )
                        snapshot.getValue(UserEntity::class.java)?.let { user ->
                            pressure.add(user)
                        }
                        //ничего с ними не делаем
                    } catch (exc: DatabaseException) {
                        exc.printStackTrace()
                    }
                }
                data = pressure
                onSuccess.invoke(data)
            }
    }

    override fun addUser(userEntity: UserEntity) {
        updateUser(userEntity)
    }

    override fun getUser(): List<UserEntity> = data


    override fun removeUser(userEntity: UserEntity) {
        database.reference.child(userEntity.id).removeValue()
    }

    override fun updateUser(changedUser: UserEntity) {
        database.reference.child(changedUser.id).setValue(changedUser)
    }
}