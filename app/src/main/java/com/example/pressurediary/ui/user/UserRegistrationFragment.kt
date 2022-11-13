package com.example.pressurediary.ui.user

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pressurediary.databinding.FragmentUserRegistrationBinding
import com.example.pressurediary.domain.entities.UserEntity
import com.example.pressurediary.domain.repos.UserRepo
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.android.inject

class UserRegistrationFragment : Fragment() {

    private var _binding: FragmentUserRegistrationBinding? = null
    private val binding get() = _binding!!

    private val myAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val userRepo: UserRepo by inject() //получили через Koin

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

            if (login.isEmpty() || password.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            } else {
                myAuth.createUserWithEmailAndPassword(login, password.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            onWindowDialog()
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Ошибка регистрации!!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }

    private fun onWindowDialog() {
        // всплывающее окно (уточнее действия)!!!
        AlertDialog.Builder(requireContext())
            .setTitle("Учетная запись создана\nЗавершить регистрацию?")//сообщение на всплыв. окне
            .setPositiveButton("ДА") { dialogInterface: DialogInterface, i: Int ->
                getController().onSuccess()//выход (кнопка назад)
                dialogInterface.dismiss()//закрываем окно. Обязательно!!
            }
            .setNegativeButton("НЕТ") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()//закрываем окно
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

    companion object {
        @JvmStatic
        fun newInstance() = UserRegistrationFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}