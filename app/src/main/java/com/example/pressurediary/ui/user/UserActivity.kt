package com.example.pressurediary.ui.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pressurediary.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(),
    UserRegistrationFragment.Controller {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

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