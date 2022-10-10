package com.example.pressurediary.ui.root

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pressurediary.ui.advice.AdviceFragment
import com.example.pressurediary.R
import com.example.pressurediary.databinding.ActivityRootBinding
import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.ui.details.DetailsBpFragment
import com.example.pressurediary.ui.diary.BpListFragment
import com.example.pressurediary.ui.settings.AboutAppFragment
import com.example.pressurediary.ui.settings.SettingsFragment
import java.lang.IllegalStateException

private const val TAG_MAIN_CONTAINER_LAYOUT_KEY = "TAG_MAIN_CONTAINER_LAYOUT_KEY"
private const val TAG_DETAILS_BP_KEY = "TAG_DETAILS_BP_KEY"
private const val TAG_ADD_DETAILS_BP_KEY = "TAG_ADD_DETAILS_BP_KEY"

class RootActivity : AppCompatActivity(),
    BpListFragment.Controller,
    DetailsBpFragment.Controller,
    SettingsFragment.Controller,
    AdviceFragment.Controller,
    AboutAppFragment.Controller {

    private lateinit var binding: ActivityRootBinding
    var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavBar.setOnItemSelectedListener {
            title = it.title
            val fragment = when (it.itemId) {
                R.id.bp_list_item -> BpListFragment()
                R.id.advice_item -> AdviceFragment()
                R.id.settings_item -> SettingsFragment()
                else -> throw IllegalStateException("Такого фрагмента нет")
            }

            swapFragment(fragment)
            Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
            return@setOnItemSelectedListener true

        }
        //значение по умилчанию (экран)
        binding.bottomNavBar.selectedItemId = R.id.bp_list_item
    }

    private fun swapFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                binding.fragmentContainerFrameLayout.id,
                fragment,
                TAG_MAIN_CONTAINER_LAYOUT_KEY
            )
//            .addToBackStack(null)
            .commit()
    }

    private fun openDetailsBpFragment(bpEntity: BpEntity) {
        val fragment: Fragment = DetailsBpFragment.newInstance(bpEntity)
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainerFrameLayout.id, fragment, TAG_DETAILS_BP_KEY)
            .addToBackStack(null)
            .commit()
    }

    private fun addDetailBpFragment() {
        val fragment: Fragment = DetailsBpFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainerFrameLayout.id, fragment, TAG_ADD_DETAILS_BP_KEY)
            .addToBackStack(null)
            .commit()
    }

    private fun onAboutApp() {
        val fragment: Fragment = AboutAppFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainerFrameLayout.id, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        exitingApplicationDoubleClick()
    }

    //выход из приложения по двойному нажатию на кнопку
    private fun exitingApplicationDoubleClick() {
        if (System.currentTimeMillis() - backPressedTime <= 3_000) {
            super.onBackPressed()
//            title =
            backPressedTime = 0//обнуляем время если вышли из фрагмента
        } else {
            Toast.makeText(
                this,
                "Нажмите еще раз, чтобы выйти из приложения", Toast.LENGTH_LONG
            )
                .show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    override fun openDetailsBp(bpEntity: BpEntity) {
        openDetailsBpFragment(bpEntity)
        title = "Подробности"
    }

    override fun addDetailBp() {
        addDetailBpFragment()
    }

    override fun onDataChanged() {
        val fragment =
            supportFragmentManager.findFragmentByTag(TAG_MAIN_CONTAINER_LAYOUT_KEY) as BpListFragment
        fragment.onDataChanged()
    }

    override fun openAboutApp() {
        onAboutApp()
        title = "О приложении"
    }
}