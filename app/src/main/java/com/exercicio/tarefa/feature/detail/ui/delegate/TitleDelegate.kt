package com.exercicio.tarefa.feature.detail.ui.delegate

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.exercicio.tarefa.R
import com.exercicio.tarefa.feature.detail.domain.entity.ProfileData
import com.exercicio.tarefa.util.extensions.inflate
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import kotlinx.android.synthetic.main.item_delegate_profile.view.*

class TitleDelegate: AbsListItemAdapterDelegate<ProfileData, Any, TitleDelegate.ViewHolder>() {

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_delegate_profile)) {

        fun bind(data: ProfileData): Unit = with(itemView) {
                txtTitle.text = data.detail.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup) = ViewHolder(parent)
    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int) = item is ProfileData
    override fun onBindViewHolder(item: ProfileData, viewHolder: ViewHolder, payloads: MutableList<Any>) = viewHolder.bind(item)

}