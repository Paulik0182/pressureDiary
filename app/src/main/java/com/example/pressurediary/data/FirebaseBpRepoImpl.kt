package com.example.pressurediary.data

import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.domain.interactors.LoginInteractor
import com.example.pressurediary.domain.repos.BpRepo
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

const val DATABASE_URL_KEY =
    "https://pressure-diary-78d25-default-rtdb.europe-west1.firebasedatabase.app/"

class FirebaseBpRepoImpl(
    private val loginInteractor: LoginInteractor
) : BpRepo {

    private var data: List<BpEntity> = emptyList()

    private val database by lazy {
        Firebase.database(DATABASE_URL_KEY).apply {
            setPersistenceEnabled(true)
            reference.keepSynced(true)
        }
    }

    private val userDiaryDatabaseReference
        get() =
            database.reference.child(loginInteractor.getUser()!!.id).child("diary")


    override fun getAllBpList(onSuccess: (List<BpEntity>) -> Unit) {
        database.reference.keepSynced(true)

        userDiaryDatabaseReference.get()
            .addOnSuccessListener {
                val pressure: MutableList<BpEntity> = mutableListOf()
                it.children.forEach { snapshot ->
                    try {
                        snapshot.getValue(BpEntity::class.java)?.let { bp ->
                            pressure.add(bp)
                        }
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
        userDiaryDatabaseReference.child(changedBp.id).setValue(changedBp)
    }

    override fun clearCache() {
        userDiaryDatabaseReference.keepSynced(false)
    }
}