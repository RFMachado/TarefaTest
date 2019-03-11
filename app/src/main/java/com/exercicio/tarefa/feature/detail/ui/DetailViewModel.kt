package com.exercicio.tarefa.feature.detail.ui

import android.arch.lifecycle.MutableLiveData
import com.exercicio.tarefa.feature.detail.domain.DetailSource
import com.exercicio.tarefa.feature.detail.domain.entity.Detail
import com.exercicio.tarefa.util.rx.ReactiveViewModel
import com.exercicio.tarefa.util.rx.RxUtils
import io.reactivex.rxkotlin.plusAssign

class DetailViewModel(private val source: DetailSource): ReactiveViewModel() {

    val uiData = MutableLiveData<ResultUIModel>()

    fun fetchItems(itemId: String) {
        disposables += source.fetchDetail(itemId)
            .compose(RxUtils.applySingleSchedulers())
            .doOnSubscribe { loadData.value = true }
            .doFinally { loadData.value = false }
            .subscribe(
                { uiData.value = ResultUIModel(detailData = it) },
                { uiData.value = ResultUIModel(error = it) }
            )
    }

    data class ResultUIModel(val detailData: Detail? = null, val error: Throwable? = null)

}