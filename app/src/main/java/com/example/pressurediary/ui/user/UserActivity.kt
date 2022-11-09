package com.example.pressurediary.ui.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pressurediary.databinding.ActivityUserBinding
import com.example.pressurediary.domain.repos.UserRepo
import org.koin.android.ext.android.inject

class UserActivity : AppCompatActivity(),
    UserRegistrationFragment.Controller {

    private val userRepo: UserRepo by inject() //получили через Koin

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        //проверка логина (регистрация)
//        userRepo.getUser()?.let {
//            onSuccess()
//        }

        // чтобы не пересоздавалась активити, делаем проверку
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.fragmentContainer.id, UserRegistrationFragment())
                .commit()
        }
    }

    override fun onSuccess() {
        finish()
    }
}