package com.exercicio.tarefa.feature.main.ui

import android.arch.lifecycle.MutableLiveData
import com.exercicio.tarefa.feature.main.domain.MainSource
import com.exercicio.tarefa.util.rx.ReactiveViewModel
import com.exercicio.tarefa.util.rx.RxUtils
import io.reactivex.rxkotlin.plusAssign

class MainViewModel(private val source: MainSource): ReactiveViewModel() {

    val uiData = MutableLiveData<ResultUIModel>()

    fun fetchItems() {
        disposables += source.getItems()
            .compose(RxUtils.applySingleSchedulers())
            .doOnSubscribe { loadData.value = true }
            .doFinally { loadData.value = false }
            .subscribe(
                { uiData.value = ResultUIModel(itemsData = it.itemsList) },
                { uiData.value = ResultUIModel(error = it) }
            )
    }


    data class ResultUIModel(val itemsData: List<String>? = null, val error: Throwable? = null)

}