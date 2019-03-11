package com.exercicio.tarefa.util

import android.content.Context

object Util {
    fun dpToPx(context: Context?, dp: Float): Int {
        if (context == null)
            return 0

        val density = context.resources.displayMetrics.density
        return Math.round(dp * density)
    }

    fun pxToDp(context: Context?, px: Float): Int {
        if (context == null)
            return 0

        val density = context.resources.displayMetrics.density
        return Math.round(px / density)
    }
}