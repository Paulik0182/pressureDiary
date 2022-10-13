package com.example.pressurediary.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.pressurediary.R
import com.example.pressurediary.domain.entities.BpEntity

class DetailsActivity : AppCompatActivity(),
    DetailsBpFragment.Controller {

    @RequiresApi(33)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val fragment = if (intent.hasExtra("BP_ENTITY_DETAILS_KEY")){
            val bpEntity = intent.extras?.getParcelable("BP_ENTITY_DETAILS_KEY") as? BpEntity
            if (bpEntity != null){
                DetailsBpFragment.newInstance(bpEntity)
            } else{
                DetailsBpFragment.newInstance()
            }
        }else{
            DetailsBpFragment.newInstance()
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onDataChanged() {
        finish()
    }
}