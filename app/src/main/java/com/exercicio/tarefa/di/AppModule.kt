package com.exercicio.tarefa.di

import com.exercicio.tarefa.feature.detail.domain.DetailSource
import com.exercicio.tarefa.feature.detail.repository.DetailRepository
import com.exercicio.tarefa.feature.detail.ui.DetailViewModel
import com.exercicio.tarefa.feature.main.domain.MainSource
import com.exercicio.tarefa.feature.main.repository.MainRepository
import com.exercicio.tarefa.feature.main.ui.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single<MainSource> { MainRepository(get()) }
    single<DetailSource> { DetailRepository(get()) }


    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}