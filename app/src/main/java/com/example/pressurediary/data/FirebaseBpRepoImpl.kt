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

    private var idCounter: Long = 0L

    //ссылка на бд. reference - это ссылка на значение
    private val database by lazy {
        Firebase.database(DATABASE_URL_KEY).apply { setPersistenceEnabled(true) }
    }    //setPersistenceEnabled(true) для работы без интернета

    init {
        database.reference.child("Значение").setValue("привет")
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
                onSuccess.invoke(pressure)
            }
    }

    override fun addBp(bpEntity: BpEntity) {
        bpEntity.id =
            ++idCounter//добавляем к id по одному значению. Сначало плюс один, а потом читаем значение
        database.reference.get().addOnSuccessListener {
            bpEntity.timeInMs = bpEntity.timeInMs
            bpEntity.systolicLevel = bpEntity.systolicLevel
            bpEntity.diastolicLevel = bpEntity.diastolicLevel
            bpEntity.pulse = bpEntity.pulse
            bpEntity.conditionUser = bpEntity.conditionUser
            bpEntity.wellBeing = bpEntity.wellBeing
        }
    }


    override fun getAllBpList(): List<BpEntity> {
        return listOf()
    }

    override fun removeBp(bpEntity: BpEntity) {
        TODO("Not yet implemented")
    }

    override fun updateBp(changedBp: BpEntity) {
        val oldBpList: BpEntity? = null

        if (oldBpList == null) {
            addBp(changedBp)//если не нашли заметку, то добавляем ее
        } else {
            database.reference.child("hj").setValue {
                oldBpList.timeInMs = changedBp.timeInMs
                oldBpList.systolicLevel = changedBp.systolicLevel
                oldBpList.diastolicLevel = changedBp.diastolicLevel
                oldBpList.pulse = changedBp.pulse
                oldBpList.conditionUser = changedBp.conditionUser
                oldBpList.wellBeing = changedBp.wellBeing
            }
        }
    }
}