package com.exercicio.tarefa.feature.detail.repository

import com.exercicio.tarefa.feature.api.ServerApi
import com.exercicio.tarefa.feature.detail.domain.DetailSource
import com.exercicio.tarefa.feature.detail.domain.entity.Detail
import com.exercicio.tarefa.feature.detail.repository.mapper.DetailMapper
import io.reactivex.Single

class DetailRepository(private val serverApi: ServerApi): DetailSource {

    override fun fetchDetail(itemId: String): Single<Detail> {
        return serverApi.fetchDetail(itemId)
            .map { DetailMapper.map(it) }
    }
}
