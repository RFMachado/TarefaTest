package com.exercicio.tarefa.feature.detail.ui.delegate

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.exercicio.tarefa.R
import com.exercicio.tarefa.util.extensions.inflate
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import kotlinx.android.synthetic.main.item_delegate_description.view.*

class DescriptionDelegate: AbsListItemAdapterDelegate<String, Any, DescriptionDelegate.ViewHolder>() {

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_delegate_description)) {

        fun bind(description: String): Unit = with(itemView) {
            txtDescription.text = description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup) = ViewHolder(parent)
    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int) = item is String
    override fun onBindViewHolder(item: String, viewHolder: ViewHolder, payloads: MutableList<Any>) = viewHolder.bind(item)

}