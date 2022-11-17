package com.example.pressurediary.domain.interactors

import com.example.pressurediary.domain.BpEvaluation
import com.example.pressurediary.domain.entities.BpEntity

/**
 * Для преобразования данных, для подписки на изменения
 * возвращает оценку измерений
 */

interface BpEvaluator {
    fun evaluate(bpEntity: BpEntity): BpEvaluation


}