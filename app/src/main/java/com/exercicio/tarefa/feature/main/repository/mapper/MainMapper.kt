package com.exercicio.tarefa.feature.main.repository.mapper

import com.exercicio.tarefa.feature.main.domain.entity.Items
import com.exercicio.tarefa.feature.main.repository.model.ItemsPayload

object MainMapper {

    fun map(payload: ItemsPayload) = Items(
        itemsList = payload.items
    )
}