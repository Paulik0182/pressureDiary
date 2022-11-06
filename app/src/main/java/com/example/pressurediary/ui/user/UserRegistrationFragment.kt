package com.example.pressurediary.ui.user

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pressurediary.databinding.FragmentUserRegistrationBinding
import com.example.pressurediary.domain.entities.UserEntity
import com.example.pressurediary.domain.repos.UserRepo
import org.koin.android.ext.android.inject

class UserRegistrationFragment : Fragment() {

    private var _binding: FragmentUserRegistrationBinding? = null
    private val binding get() = _binding!!

    private val userRepo: UserRepo by inject() //получили через Koin

    private lateinit var userEntity: UserEntity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserRegistrationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveChangesButton.visibility = View.INVISIBLE
        binding.recoverPasswordButton.visibility = View.INVISIBLE

//        setUserEntity()

        onClickRegistrationUser()
    }

    private fun setUserEntity() {
        val pasOne = binding.passwordOneEditText
        val pasTwo = binding.passwordTwoEditText

        binding.emailEditText.setText(userEntity.email)
        binding.nameEditText.setText(userEntity.name)

        if (pasOne == pasTwo) {
            binding.passwordOneEditText.setText(userEntity.password)
        } else {
            Toast.makeText(
                requireContext(), "Введен не правильно пароль!" +
                        "\nПовторите ввод пароля.", Toast.LENGTH_SHORT
            ).show()
            binding.passwordOneEditText.text = null
            binding.passwordTwoEditText.text = null
        }
    }

    private fun onClickRegistrationUser() {
        binding.registrationButton.setOnClickListener {
            //Делаем копию данных чтобы потом изменить часть данных.
            val changedUserEntity = userEntity
                .copy(
                    name = binding.nameEditText.text.toString(),
                    email = binding.emailEditText.text.toString(),
                    password = binding.passwordOneEditText.text.toString().toInt()
                )

            val userRepo = userRepo
            userRepo.updateUser(changedUserEntity)//добавили новые данные
            getController().onDataChanged()//обновили данные
//            activity?.onBackPressed()//выход

            Toast.makeText(
                requireContext(),
                "Сохранить",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    interface Controller {
        fun onDataChanged()
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