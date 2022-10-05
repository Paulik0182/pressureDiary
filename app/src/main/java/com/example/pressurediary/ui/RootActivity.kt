package com.example.pressurediary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pressurediary.ui.advice.AdviceFragment
import com.example.pressurediary.R
import com.example.pressurediary.databinding.ActivityRootBinding
import com.example.pressurediary.ui.diary.BpListFragment
import com.example.pressurediary.ui.settings.SettingsFragment
import java.lang.IllegalStateException

class RootActivity : AppCompatActivity(),
    BpListFragment.Controller {

    private lateinit var binding: ActivityRootBinding

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
            .replace(binding.fragmentContainerFrameLayout.id, fragment)
//            .addToBackStack(null)
            .commit()
    }
}