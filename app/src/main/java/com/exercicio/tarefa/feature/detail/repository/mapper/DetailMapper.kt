package com.exercicio.tarefa.feature.detail.repository.mapper

import com.exercicio.tarefa.feature.detail.domain.entity.Comment
import com.exercicio.tarefa.feature.detail.domain.entity.Detail
import com.exercicio.tarefa.feature.detail.repository.model.CommentsPayload
import com.exercicio.tarefa.feature.detail.repository.model.DetailPayload

object DetailMapper {
    fun map(payload: DetailPayload) = Detail (
        id = payload.id,
        city = payload.city,
        neighborhood = payload.neighborhood,
        urlPhoto = payload.urlPhoto,
        urlLogo = payload.urlLogo,
        title = payload.title,
        phone = payload.phone,
        text = payload.text,
        address = payload.address,
        lat = payload.lat,
        lng = payload.lng,
        comments = map(payload.comments)
    )


    fun map(payloads: List<CommentsPayload>) = payloads.map { map(it) }

    fun map(payload: CommentsPayload) = Comment (
        urlPhoto = payload.urlPhoto,
        name = payload.name,
        note = payload.note,
        title = payload.title,
        textComment = payload.txtComment
    )

}