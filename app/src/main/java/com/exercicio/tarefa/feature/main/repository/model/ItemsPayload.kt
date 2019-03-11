package com.exercicio.tarefa.feature.main.repository.model

import com.google.gson.annotations.SerializedName

class ItemsPayload {
    @SerializedName("lista")
    lateinit var items: List<String>

}