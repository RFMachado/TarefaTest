package com.exercicio.tarefa.feature.main.domain

import com.exercicio.tarefa.feature.main.domain.entity.Items
import io.reactivex.Single

interface MainSource {
    fun getItems() : Single<Items>
}