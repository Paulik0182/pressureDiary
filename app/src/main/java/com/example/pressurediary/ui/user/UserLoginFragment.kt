package com.example.pressurediary.ui.user

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pressurediary.databinding.FragmentUserLoginBinding
import com.example.pressurediary.domain.entities.UserEntity
import com.example.pressurediary.domain.repos.UserRepo
import org.koin.android.ext.android.inject

private const val USER_KEY = "USER_KEY"

class UserLoginFragment : Fragment() {

    private var _binding: FragmentUserLoginBinding? = null
    private val binding get() = _binding!!

    private val userRepo: UserRepo by inject() //получили через Koin

    private lateinit var userEntity: UserEntity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            getController().openBpListFragment()
            //TODO нужна проверка логина
        }

        binding.entryTextView.setOnClickListener {
            getController().openBpListFragment()
        }

        binding.registrationTextView.setOnClickListener {
            getController().openRegistrationUserFragment()
        }

//        setUserEntity()
    }

    private fun setUserEntity() {

        binding.nameEditText.setText(userEntity.name)
        binding.passwordEditText.setText(userEntity.password)
    }

    interface Controller {
        fun openBpListFragment()
        fun openRegistrationUserFragment()
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {
        @JvmStatic
        fun newInstance() = UserLoginFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}