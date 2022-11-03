package com.example.pressurediary.data

import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.domain.repos.BpRepo
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/**
 * DataSnapshot - это срез данных который приходит
 */
const val DATABASE_URL_KEY =
    "https://pressure-diary-78d25-default-rtdb.europe-west1.firebasedatabase.app/"

class FirebaseBpRepoImpl : BpRepo {

    private var data: List<BpEntity> = emptyList()

    //ссылка на бд. reference - это ссылка на значение
    private val database by lazy {
        Firebase.database(DATABASE_URL_KEY).apply { setPersistenceEnabled(true) }
    }    //setPersistenceEnabled(true) для работы без интернета

    private var successBp: ((List<BpEntity>) -> Unit)? = null


    init {
        database.reference.child("Значение").setValue("'это я'")

        database.reference.keepSynced(true)//для синхранизации данных (кешируем данные)

        database.reference.get()
            .addOnSuccessListener {
                val pressure: MutableList<BpEntity> = mutableListOf()// собираем колекцию
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
                successBp?.invoke(data)
            }
    }

    override fun getAllBpList(onSuccess: (List<BpEntity>) -> Unit) {
        database.reference.keepSynced(true)//для синхранизации данных (кешируем данные)

        database.reference.get()
            .addOnSuccessListener {
                val pressure: MutableList<BpEntity> = mutableListOf()// собираем колекцию
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
        //TODO
    }


    override fun getAllBpList(): List<BpEntity> = data

    override fun removeBp(bpEntity: BpEntity) {
        TODO("Not yet implemented")
    }

    override fun updateBp(changedBp: BpEntity) {
        //TODO
    }
}