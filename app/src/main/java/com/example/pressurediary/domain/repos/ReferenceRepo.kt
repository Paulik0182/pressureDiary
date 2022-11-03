package com.example.pressurediary.domain.repos

import com.example.pressurediary.domain.entities.ReferenceEntity

interface ReferenceRepo {
    fun addReference(referenceEntity: ReferenceEntity)//добавить
    fun getReference(): List<ReferenceEntity>//получить
}