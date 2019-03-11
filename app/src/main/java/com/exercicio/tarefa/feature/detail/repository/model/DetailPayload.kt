package com.exercicio.tarefa.feature.detail.repository.model

import com.google.gson.annotations.SerializedName

class DetailPayload {
    @SerializedName("id")
    lateinit var id: String

    @SerializedName("cidade")
    lateinit var city: String

    @SerializedName("bairro")
    lateinit var neighborhood: String

    @SerializedName("urlFoto")
    lateinit var urlPhoto: String

    @SerializedName("urlLogo")
    lateinit var urlLogo: String

    @SerializedName("titulo")
    lateinit var title: String

    @SerializedName("texto")
    lateinit var text: String

    @SerializedName("telefone")
    lateinit var phone: String

    @SerializedName("endereco")
    lateinit var address: String

    @SerializedName("latitude")
    var lat: Double = 0.0

    @SerializedName("longitude")
    var lng: Double = 0.0

    @SerializedName("comentarios")
    lateinit var comments: List<CommentsPayload>

}

class CommentsPayload {
    @SerializedName("urlFoto")
    lateinit var urlPhoto: String

    @SerializedName("nome")
    lateinit var name: String

    @SerializedName("nota")
    var note: Int = 0

    @SerializedName("titulo")
    lateinit var title: String

    @SerializedName("comentario")
    lateinit var txtComment: String
}