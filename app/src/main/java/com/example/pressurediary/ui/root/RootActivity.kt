package com.example.pressurediary.ui.root

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pressurediary.R
import com.example.pressurediary.databinding.ActivityRootBinding
import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.ui.chart.ChartFragment
import com.example.pressurediary.ui.details.DetailsActivity
import com.example.pressurediary.ui.diary.BpListFragment
import com.example.pressurediary.ui.settings.AboutAppFragment
import com.example.pressurediary.ui.settings.SettingsFragment
import com.example.pressurediary.ui.settings.reference.ReferenceFragment
import com.example.pressurediary.ui.user.UserActivity
import com.example.pressurediary.ui.user.UserLoginFragment

private const val TAG_MAIN_CONTAINER_LAYOUT_KEY = "TAG_MAIN_CONTAINER_LAYOUT_KEY"
private const val BP_ENTITY_DETAILS_KEY = "BP_ENTITY_DETAILS_KEY"
private const val DETAILS_REQUEST_KOD = 100
private const val REGISTRATION_USER_REQUEST_KOD = 200

class RootActivity : AppCompatActivity(),
    BpListFragment.Controller,
    SettingsFragment.Controller,
    ChartFragment.Controller,
    AboutAppFragment.Controller,
    ReferenceFragment.Controller,
    UserLoginFragment.Controller {

    private lateinit var binding: ActivityRootBinding
    var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null)//проверяем какой запуск первый или нет (например, после поворота экрана)
            supportFragmentManager
                .beginTransaction()
                .replace(binding.fragmentContainerFrameLayout.id, UserLoginFragment())
                .commit()
        binding.bottomNavBar.visibility = View.INVISIBLE

//        binding.bottomNavBar.setOnItemSelectedListener {
//            title = it.title
//            val fragment = when (it.itemId) {
//                R.id.bp_list_item -> BpListFragment()
//                R.id.chart_item -> ChartFragment()
//                R.id.settings_item -> SettingsFragment()
//                else -> throw IllegalStateException("Такого фрагмента нет")
//            }
//
//            swapFragment(fragment)
//            return@setOnItemSelectedListener true
//
//        }
//        //значение по умилчанию (экран)
//        if (savedInstanceState == null) {
//            binding.bottomNavBar.selectedItemId = R.id.bp_list_item
//        } else {
//            //TODO
//        }
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

    override fun openDetailsBp(bpEntity: BpEntity?) {
        val intent = Intent(this, DetailsActivity::class.java)
        bpEntity?.let {
            intent.putExtra(BP_ENTITY_DETAILS_KEY, bpEntity)
        }
        startActivityForResult(intent, DETAILS_REQUEST_KOD)
    }

    private fun onAboutApp() {
        val fragment: Fragment = AboutAppFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainerFrameLayout.id, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun onReferenceApp() {
        val fragment: Fragment = ReferenceFragment.newInstance()
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

    override fun openAboutApp() {
        onAboutApp()
        title = "О приложении"
    }

    override fun openReferenceApp() {
        onReferenceApp()
        title = "Справка"
    }

    override fun openBpListFragment() {
        binding.bottomNavBar.visibility = View.VISIBLE
//        binding.fragmentContainerFrameLayout.visibility = View.INVISIBLE
        binding.bottomNavBar.setOnItemSelectedListener {
            title = it.title
            val fragment = when (it.itemId) {
                R.id.bp_list_item -> BpListFragment()
                R.id.chart_item -> ChartFragment()
                R.id.settings_item -> SettingsFragment()
                else -> throw IllegalStateException("Такого фрагмента нет")
            }
            swapFragment(fragment)
            return@setOnItemSelectedListener true
        }
        binding.bottomNavBar.selectedItemId = R.id.bp_list_item
    }

    override fun openRegistrationUserFragment() {
        val intent = Intent(this, UserActivity::class.java)
        startActivityForResult(intent, REGISTRATION_USER_REQUEST_KOD)
    }
}