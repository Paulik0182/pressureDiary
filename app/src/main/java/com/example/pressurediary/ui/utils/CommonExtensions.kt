package com.example.pressurediary.ui.utils

/**
 * Для проверки каждого значения на null. Используется при идентификации.
 */

inline fun <T : Any> ifLet(vararg elements: T?, closure: (List<T>) -> Unit) {
    if (elements.all { it != null }) {
        closure(elements.filterNotNull())
    }
}