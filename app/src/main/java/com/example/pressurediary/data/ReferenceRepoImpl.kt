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
                0,
                "MP Android Chart",
                "В данном приложении была использована библиотека MP Android Chart.  " +
                        "\nCopyright 2020 Philipp Jahoda\n" +
                        "\n" +
                        "Licensed under the Apache License, Version 2.0 (the \"License\"); " +
                        "you may not use this file except in compliance with the License. Y" +
                        "ou may obtain a copy of the License at\n" +
                        "\n" +
                        "http://www.apache.org/licenses/LICENSE-2.0\n" +
                        "\n" +
                        "Unless required by applicable law or agreed to in writing, software " +
                        "distributed under the License is distributed on an \"AS IS\" BASIS, " +
                        "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. " +
                        "See the License for the specific language governing permissions and " +
                        "limitations under the License.",
                R.drawable.mp_android_chart,
                "http://github.com/PhilJay/MPAndroidChart"
            )
        )
        data.add(
            ReferenceEntity(
                1,
                "Firebase",
                "Firebase - используется для аутентификации, доступа к данным, " +
                        "отслеживания ошибок и сбоев в приложении." +
                        "\nFirebase сертифицирован в соответствии с основными стандартами " +
                        "конфиденциальности и безопасности " +
                        "\n(https://firebase.google.com/support/privacy/)",
                R.drawable.firebase,
                "https://firebase.google.com/"
            )
        )
    }
}