package com.example.pressurediary.data

import com.example.pressurediary.domain.BpEvaluation
import com.example.pressurediary.domain.entities.BpEntity
import com.example.pressurediary.domain.interactors.BpEvaluator

/**
 * класс для оценки уровня давления
 */

class BpEvaluatorImpl: BpEvaluator {

    override fun evaluate(bpEntity: BpEntity): BpEvaluation =
        when (bpEntity.systolicLevel){
            in 1..119 -> BpEvaluation.NORMAL
            in 120..139 -> BpEvaluation.PRE_HYPERTENSION
            in 140..159 -> BpEvaluation.HYPERTENSION_1
            in 160..180 -> BpEvaluation.HYPERTENSION_2
            else -> BpEvaluation.UNKNOWN
        }
}