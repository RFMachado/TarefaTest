package com.exercicio.tarefa

import android.app.Application
import com.exercicio.tarefa.di.appModule
import com.exercicio.tarefa.di.netModule
import org.koin.android.ext.android.startKoin

class TarefaApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, netModule))
    }

}