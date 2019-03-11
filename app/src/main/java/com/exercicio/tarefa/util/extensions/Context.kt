package com.exercicio.tarefa.util.extensions

import android.content.Context
import android.support.v7.app.AlertDialog
import com.exercicio.tarefa.R

inline fun Context.alert(func: AlertDialog.Builder.() -> AlertDialog.Builder): AlertDialog {
    return AlertDialog.Builder(this, R.style.AppThemeAlertDialog).func().show()
}