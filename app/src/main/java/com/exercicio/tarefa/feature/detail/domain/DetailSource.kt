package com.exercicio.tarefa.feature.detail.domain

import com.exercicio.tarefa.feature.detail.domain.entity.Detail
import io.reactivex.Single

interface DetailSource {
    fun fetchDetail(itemId: String) : Single<Detail>
}