package com.exercicio.tarefa.util.extensions

import android.view.View

inline fun View.hide() {
    visibility = View.GONE
}

inline fun View.show() {
    visibility = View.VISIBLE
}