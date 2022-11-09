package com.example.pressurediary.ui.user

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pressurediary.R
import com.example.pressurediary.databinding.FragmentUserLoginBinding
import com.example.pressurediary.domain.entities.UserEntity
import com.example.pressurediary.domain.interactors.LoginInteractor
import com.example.pressurediary.domain.repos.UserRepo
import org.koin.android.ext.android.inject

class UserLoginFragment : Fragment(R.layout.fragment_user_login) {

    private var _binding: FragmentUserLoginBinding? = null
    private val binding get() = _binding!!

    private val loginInteractor: LoginInteractor by inject()
    private val userRepo: UserRepo by inject()

    private lateinit var userEntity: UserEntity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //вариант для разметки
        _binding = FragmentUserLoginBinding.bind(view)

        binding.loginButton.setOnClickListener {
            val login = binding.nameEditText.text.toString()
            val password =
                try {
                    binding.passwordEditText.text.toString().toInt()
                } catch (e: Exception) {
                    0
                }
            loginInteractor.login(login, password) {
                it?.let {
                    // передтем как зайти, нужно сохранить логин и пароль в UserRepo
                    userRepo.addUser(it)
                    getController().onSuccessLogin()
                } ?: showError()
            }
        }

        binding.anonymousLoginTextView.setOnClickListener {
            // для ананимного пользователя также заводим нового пользователя
            getController().onSuccessLogin()
        }

        binding.registrationTextView.setOnClickListener {
            getController().openRegistrationUser()
        }

//        setUserEntity()
    }

    private fun showError() {
        Toast.makeText(requireContext(), "Не верно введен пароль!!", Toast.LENGTH_SHORT).show()
    }

    interface Controller {
        fun onSuccessLogin()
        fun openRegistrationUser()
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}