package com.example.pressurediary.data

import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.domain.interactors.LoginInteractor
import com.example.pressurediary.domain.repos.BpRepo
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/**
 * DataSnapshot - это срез данных который приходит
 * get() - это один вызов, то-есть дождались значение и показали. Разовое получение данных
 */
const val DATABASE_URL_KEY =
    "https://pressure-diary-78d25-default-rtdb.europe-west1.firebasedatabase.app/"

class FirebaseBpRepoImpl(
    private val loginInteractor: LoginInteractor
) : BpRepo {

    private var data: List<BpEntity> = emptyList()

    //ссылка на бд. reference - это ссылка на значение
    private val database by lazy {
        Firebase.database(DATABASE_URL_KEY).apply {
            setPersistenceEnabled(true) //для работы без интернета
            reference.keepSynced(true) //для синхранизации данных (кешируем данные)
        }
    }

    //перед тем как что-то достаем из БД начинаем знать о пользователе
    private val userDiaryDatabaseReference
        get() =
            database.reference.child(loginInteractor.getUser()!!.id).child("diary")


    override fun getAllBpList(onSuccess: (List<BpEntity>) -> Unit) {
        database.reference.keepSynced(true)//для синхранизации данных (кешируем данные)

        //перед тем как что-то достаем из БД начинаем знать о пользователе
//        val userId = userRepo.getUser()!!.id
        // распарс юзера

        userDiaryDatabaseReference.get()
            .addOnSuccessListener {
                val pressure: MutableList<BpEntity> = mutableListOf()// собираем колекцию
                //ищем все записи (snapshot)
                it.children.forEach { snapshot ->
                    //обработка исключения. Если есть соответствующие данные то обрабатываем,
                    // если данные не соответствуют то ничего с ними не делаем
                    try {
                        //Парсим значения. Если значение не null то добавляем в колекцию (lessons.add(it) )
                        snapshot.getValue(BpEntity::class.java)?.let { bp ->
                            pressure.add(bp)
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

    override fun addBp(bpEntity: BpEntity) {
        updateBp(bpEntity)
    }

    override fun getAllBpList(): List<BpEntity> = data

    override fun removeBp(bpEntity: BpEntity) {
        userDiaryDatabaseReference.child(bpEntity.id).removeValue()
    }

    override fun updateBp(changedBp: BpEntity) {
        //push() - создает новую запись с индивидуальным кодом (ключь) и уже туда помещает данные
//        database.reference.push().setValue(changedBp)
        //создаем новую запись с id
        userDiaryDatabaseReference.child(changedBp.id).setValue(changedBp)
    }

    override fun clearCache() {
        userDiaryDatabaseReference.keepSynced(false)
    }
}