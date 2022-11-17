package com.example.pressurediary.ui.user

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pressurediary.R
import com.example.pressurediary.databinding.FragmentUserRegistrationBinding
import com.example.pressurediary.domain.entities.UserEntity
import com.example.pressurediary.domain.interactors.LoginInteractor
import com.example.pressurediary.ui.utils.isEmailValid
import com.example.pressurediary.ui.utils.isPasswordValid
import com.example.pressurediary.ui.utils.toastFragment
import org.koin.android.ext.android.inject

class UserRegistrationFragment : Fragment() {

    private var _binding: FragmentUserRegistrationBinding? = null
    private val binding get() = _binding!!

    private val loginInteractor: LoginInteractor by inject()

    private lateinit var userEntity: UserEntity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveChangesButton.visibility = View.INVISIBLE
        binding.recoverPasswordButton.visibility = View.INVISIBLE

        onClickRegistrationUser()
    }

    private fun onClickRegistrationUser() {
        binding.registrationButton.setOnClickListener {
            val login = binding.emailEditText.text.toString()

            val password =
                try {
                    binding.passwordOneEditText.text.toString().toInt()
                } catch (e: Exception) {
                    0
                }

            if (login.isEmailValid()) {
                if (password.toString().isPasswordValid()) {
                    loginInteractor.register(login, password) {
                        if (it) {
                            onWindowDialog()
                        } else {
                            view?.toastFragment(getString(R.string.registration_error))
                        }
                    }
                } else {
                    view?.toastFragment(getString(R.string.not_all_fields))
                }
            } else {
                view?.toastFragment(getString(R.string.email_valid))
            }
        }
    }

    private fun onWindowDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Учетная запись создана\nЗавершить регистрацию?")
            .setPositiveButton(getText(R.string.yes)) { dialogInterface: DialogInterface, i: Int ->
                getController().onSuccess()
                dialogInterface.dismiss()
            }
            .setNegativeButton(getText(R.string.no)) { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
            .show()
    }

    interface Controller {
        fun onSuccess()
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