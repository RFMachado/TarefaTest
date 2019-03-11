package com.exercicio.tarefa.feature.detail.domain.entity

class Detail(
    val id: String,
    val city: String,
    val neighborhood: String,
    val urlPhoto: String,
    val urlLogo: String,
    val title: String,
    val phone: String,
    val text: String,
    val address: String,
    val lat: Double,
    val lng: Double,
    val comments: List<Comment>
)

class Comment (
    val urlPhoto: String,
    val name: String,
    val note: Int,
    val title: String,
    val textComment: String
)