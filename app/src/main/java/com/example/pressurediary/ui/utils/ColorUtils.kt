package com.example.pressurediary.ui.utils

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

/**
 * Для то чтобы можно было воспользоватся цветом из ресурсов (здесь это для графика)
 * textView.setTextColor(getColorFromAttr(R.attr.color))
 * pieChart.setBackgroundColor(requireContext().getColorFromAttr(R.attr.second_plan_background))
 */
@ColorInt
fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}