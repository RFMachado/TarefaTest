package com.exercicio.tarefa.feature.detail.ui

import com.exercicio.tarefa.feature.detail.ui.delegate.*
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class DetailAdapter constructor(items: List<Any>, listener: ActionsListener): ListDelegationAdapter<List<Any>>() {

    interface ActionsListener {
        fun onClickServices()
        fun onClickCall(phoneNumber: String)
        fun onClickAddress(address: String)
        fun onClickComments()
    }

    init {
        delegatesManager
            .addDelegate(TitleDelegate())
            .addDelegate(ActionsDelegate(listener))
            .addDelegate(DescriptionDelegate())
            .addDelegate(MapDelegate())
            .addDelegate(CommentsDelegate())

        setItems(items)
    }

}