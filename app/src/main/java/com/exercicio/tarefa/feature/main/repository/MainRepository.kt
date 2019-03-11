package com.exercicio.tarefa.feature.main.repository

import com.exercicio.tarefa.feature.api.ServerApi
import com.exercicio.tarefa.feature.main.domain.MainSource
import com.exercicio.tarefa.feature.main.domain.entity.Items
import com.exercicio.tarefa.feature.main.repository.mapper.MainMapper
import io.reactivex.Single

class MainRepository(private val serverApi: ServerApi): MainSource {

    override fun getItems(): Single<Items> {
        return serverApi.getItemsList()
            .map { MainMapper.map(it) }
    }

}