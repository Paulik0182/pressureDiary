package com.example.pressurediary.data

import com.example.pressurediary.domain.entities.UserEntity
import com.example.pressurediary.domain.interactors.LoginInteractor
import com.example.pressurediary.ui.utils.ifLet
import com.google.firebase.auth.FirebaseAuth

class LoginInteractorImpl(
) : LoginInteractor {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun login(login: String, password: Int, onResult: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(login, password.toString())
            .addOnCompleteListener {
                onResult(it.isSuccessful)
            }
    }

    override fun register(login: String, password: Int, onResult: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(login, password.toString())
            .addOnCompleteListener {
                onResult(it.isSuccessful)
            }
    }

    //работа с разлогированием
    override fun logout() {
        auth.signOut()
    }

    override fun getUser(): UserEntity? {
        val id = auth.currentUser?.uid
        val email = auth.currentUser?.email

        ifLet(id, email) {
            return UserEntity(
                id = it[0],
                email = it[1],
                name = it[1]
            )
        }
        return null
    }
}