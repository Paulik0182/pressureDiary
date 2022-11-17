package com.example.pressurediary.ui.user

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.pressurediary.R
import com.example.pressurediary.databinding.FragmentUserLoginBinding
import com.example.pressurediary.domain.interactors.LoginInteractor
import com.example.pressurediary.ui.utils.isEmailValid
import com.example.pressurediary.ui.utils.toastFragment
import org.koin.android.ext.android.inject

class UserLoginFragment : Fragment(R.layout.fragment_user_login) {

    private var _binding: FragmentUserLoginBinding? = null
    private val binding get() = _binding!!

    private val loginInteractor: LoginInteractor by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentUserLoginBinding.bind(view)

        binding.loginButton.setOnClickListener {

            val login = binding.nameEditText.text.toString()

            val password =
                try {
                    binding.passwordEditText.text.toString().toInt()
                } catch (e: Exception) {
                    0
                }

            if (login.isEmailValid()) {
                loginInteractor.login(login, password) {
                    if (it) {
                        getController().onSuccessLogin()
                    } else {
                        showError()
                    }
                }
            } else {
                view.toastFragment(getString(R.string.email_valid))
            }
        }

        binding.anonymousLoginTextView.setOnClickListener {
            getController().onSuccessLogin()
        }

        binding.registrationTextView.setOnClickListener {
            getController().openRegistrationUser()
        }
    }

    private fun showError() {
        view?.toastFragment(getString(R.string.invalid_password))
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