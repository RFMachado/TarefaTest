package com.exercicio.tarefa.feature.api

import com.exercicio.tarefa.feature.detail.repository.model.DetailPayload
import com.exercicio.tarefa.feature.main.repository.model.ItemsPayload
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ServerApi {

    @GET("/tarefa")
    fun getItemsList(): Single<ItemsPayload>

    @GET("/tarefa/{itemId}")
    fun fetchDetail(@Path("itemId") itemId: String): Single<DetailPayload>

}