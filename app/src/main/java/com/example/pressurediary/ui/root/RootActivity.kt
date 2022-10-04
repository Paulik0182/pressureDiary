package com.example.pressurediary.ui.root

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import com.example.pressurediary.ui.advice.AdviceFragment
import com.example.pressurediary.R
import com.example.pressurediary.databinding.ActivityRootBinding
import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.ui.details.DetailsBpFragment
import com.example.pressurediary.ui.diary.BpListFragment
import com.example.pressurediary.ui.settings.SettingsFragment
import java.lang.IllegalStateException

private const val TEG_DETAILS_BP_KEY = "TEG_DETAILS_BP_KEY"
private const val TEG_ADD_DETAILS_BP_KEY = "TEG_DETAILS_BP_KEY"

class RootActivity : AppCompatActivity(),
    BpListFragment.Controller,
    DetailsBpFragment.Controller {

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

    private fun openDetailsBpFragment(bpId: Long, bpEntity: BpEntity) {
        val fragment: Fragment = DetailsBpFragment.newInstance(bpId, bpEntity)
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainerFrameLayout.id, fragment, TEG_DETAILS_BP_KEY)
            .addToBackStack(null)
            .commit()
    }

    private fun addDetailBpFragment() {
        val fragment: Fragment = DetailsBpFragment.newAddInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainerFrameLayout.id, fragment, TEG_ADD_DETAILS_BP_KEY)
            .addToBackStack(null)
            .commit()
    }

    override fun openDetailsBp(bpId: Long, bpEntity: BpEntity) {
        openDetailsBpFragment(bpId, bpEntity)
        title = "Подробности"
    }

    override fun addDetailBp() {
        addDetailBpFragment()
    }
}