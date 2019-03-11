package com.exercicio.tarefa.feature.detail.ui.delegate

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.exercicio.tarefa.R
import com.exercicio.tarefa.feature.detail.domain.entity.ActionsData
import com.exercicio.tarefa.feature.detail.ui.DetailAdapter
import com.exercicio.tarefa.util.extensions.inflate
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import kotlinx.android.synthetic.main.item_delegate_actions.view.*

class ActionsDelegate constructor(private val listener: DetailAdapter.ActionsListener): AbsListItemAdapterDelegate<ActionsData, Any, ActionsDelegate.ViewHolder>() {

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_delegate_actions)) {

        fun bind(data: ActionsData): Unit = with(itemView) {
            btnServices.setOnClickListener {
                listener.onClickServices()
            }

            btnCall.setOnClickListener {
                listener.onClickCall(data.detail.phone)
            }

            btnAddress.setOnClickListener {
                listener.onClickAddress(data.detail.address)
            }

            btnComments.setOnClickListener {
                listener.onClickComments()
            }

            btnFavorites.setOnClickListener {

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup) = ViewHolder(parent)
    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int) = item is ActionsData
    override fun onBindViewHolder(item: ActionsData, viewHolder: ViewHolder, payloads: MutableList<Any>) = viewHolder.bind(item)

}