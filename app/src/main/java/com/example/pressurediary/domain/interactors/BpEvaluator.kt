package com.example.pressurediary.domain.interactors

import com.example.pressurediary.domain.BpEvaluation
import com.example.pressurediary.domain.entities.BpEntity

/**
 * Для преобразования данных, для подписки на изменения
 */

interface BpEvaluator {
    fun evaluate(bpEntity: BpEntity): BpEvaluation//возвращает оценку измерений


}