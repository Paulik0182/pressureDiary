package com.example.pressurediary.data

import com.example.pressurediary.R
import com.example.pressurediary.domain.entities.ReferenceEntity
import com.example.pressurediary.domain.repos.ReferenceRepo

class ReferenceRepoImpl : ReferenceRepo {

    private var data: MutableList<ReferenceEntity> = mutableListOf()

    override fun addReference(referenceEntity: ReferenceEntity) {
        data.add(referenceEntity)
    }

    override fun getReference(): List<ReferenceEntity> {
        return ArrayList(data)
    }

    init {
        data.add(
            ReferenceEntity(
                1,
                "MP Android Chart",
                "В данном приложении была использована библиотека MP Android Chart.  " +
                        "Эта библиотека, предназначенная для рисования диаграмм на Android." +
                        "\nВыражаем благодарность создателю Библиотеки ",
                R.drawable.mp_android_chart,
                "http://github.com/PhilJay/MPAndroidChart"
            )
        )
        data.add(
            ReferenceEntity(
                2,
                "MP Android Chart",
                "В данном приложении была использована библиотека MP Android Chart.  " +
                        "Эта библиотека, предназначенная для рисования диаграмм на Android." +
                        "\nВыражаем благодарность создателю Библиотеки ",
                R.drawable.mp_android_chart
            )
        )
        data.add(
            ReferenceEntity(
                3,
                "MP Android Chart",
                "В данном приложении была использована библиотека MP Android Chart.  " +
                        "Эта библиотека, предназначенная для рисования диаграмм на Android." +
                        "\nВыражаем благодарность создателю Библиотеки ",
                R.drawable.mp_android_chart
            )
        )
        data.add(
            ReferenceEntity(
                4,
                "MP Android Chart",
                "В данном приложении была использована библиотека MP Android Chart.  " +
                        "Эта библиотека, предназначенная для рисования диаграмм на Android." +
                        "\nВыражаем благодарность создателю Библиотеки ",
                R.drawable.mp_android_chart
            )
        )
    }
}