package com.exercicio.tarefa.feature.main.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.exercicio.tarefa.R
import com.exercicio.tarefa.util.extensions.inflate
import kotlinx.android.synthetic.main.item_adapter.view.*

class MainAdapter constructor(itemsList: List<String>, private val listener: (String) -> Unit):
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    val items = itemsList

    override fun onBindViewHolder(holder: ViewHolder, position: Int): Unit = with(holder.itemView) {
        val item = items[position]

        txtTitle.text = item

        setOnClickListener {
            listener.invoke(item)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  ViewHolder(parent.inflate(R.layout.item_adapter,  false))
    override fun getItemCount() = items.size
}