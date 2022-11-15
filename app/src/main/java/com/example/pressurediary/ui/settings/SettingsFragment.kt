package com.example.pressurediary.ui.settings

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.example.pressurediary.R
import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.domain.interactors.LoginInteractor
import com.example.pressurediary.domain.repos.BpRepo
import org.koin.android.ext.android.inject

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var aboutAppButton: Button
    private lateinit var shareAppButton: Button
    private lateinit var logoutButton: Button
    private lateinit var deletingRecordsButton: Button
    private lateinit var referenceAppButton: Button
    private lateinit var messageTitleTv: TextView
    private lateinit var lightDarkThemeSwitch: SwitchCompat

    private val loginInteractor: LoginInteractor by inject()
    private val bpRepo: BpRepo by inject()

    private lateinit var bpEntity: BpEntity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

//        bpEntity = requireArguments().getParcelable("BP_ENTITY_KEY")!!

        onUser()

        aboutAppButton.setOnClickListener {
            getController().openAboutApp()
        }

        shareAppButton.setOnClickListener {
            val message = messageTitleTv.text.toString()
            sendMessage(message)
        }

        referenceAppButton.setOnClickListener {
            getController().openReferenceApp()
        }

        lightDarkThemeSwitch.isChecked =
            AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

        lightDarkThemeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) {
                    AppCompatDelegate.MODE_NIGHT_YES
                } else {
                    AppCompatDelegate.MODE_NIGHT_NO
                }
            )
        }

        logoutButton.setOnClickListener {
            bpRepo.clearCache()
            loginInteractor.logout()

            getController().openLogin()
        }

        deletingRecordsButton.setOnClickListener {
            onWindowDialog()

//            if (bpEntity != null) {
//                onWindowDialog()
//            } else {
//                Toast.makeText(requireContext(), "Отсутствуют записи", Toast.LENGTH_SHORT).show()
//            }
        }
    }

    private fun onWindowDialog() {
        AlertDialog.Builder(ContextThemeWrapper(requireContext(), R.style.AlertDialogCustom))

            .setTitle("Внимание!!!\nВся история измерений будет удалина!")
            .setPositiveButton(getText(R.string.yes)) { dialogInterface: DialogInterface, i: Int ->
//                bpRepo.removeBp(bpEntity)

                dialogInterface.dismiss()
            }
            .setNegativeButton(getText(R.string.no)) { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
            .show()
    }

    @SuppressLint("SetTextI18n")
    private fun onUser() {
        val title = "Пользователь:\n"
        val nameUser = loginInteractor.getUser()?.name
        if (nameUser != null) {
            messageTitleTv.text = title + nameUser
        } else {
            messageTitleTv.text = getText(R.string.unregistered_user).toString()
        }
    }

    private fun initViews(view: View) {
        aboutAppButton = view.findViewById(R.id.about_app_button)
        shareAppButton = view.findViewById(R.id.share_app_button)
        logoutButton = view.findViewById(R.id.logout_button)
        deletingRecordsButton = view.findViewById(R.id.deleting_records_button)
        referenceAppButton = view.findViewById(R.id.reference_app_button)
        messageTitleTv = view.findViewById(R.id.message_title_text_view)
        lightDarkThemeSwitch = view.findViewById(R.id.switch_themes)
    }

    private fun sendMessage(message: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, message)
        startActivity(intent)
    }

    interface Controller {
        fun openAboutApp()
        fun openReferenceApp()
        fun openLogin()
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }
}