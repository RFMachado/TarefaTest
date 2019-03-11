package com.exercicio.tarefa.feature.detail.ui.delegate

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.exercicio.tarefa.R
import com.exercicio.tarefa.feature.detail.domain.entity.Comment
import com.exercicio.tarefa.util.extensions.inflate
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import kotlinx.android.synthetic.main.item_delegate_comments.view.*


class CommentsDelegate: AbsListItemAdapterDelegate<Comment, Any, CommentsDelegate.ViewHolder>() {

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_delegate_comments)) {

        fun bind(data: Comment): Unit = with(itemView) {
            txtName.text = data.name
            txtTitle.text = data.title
            txtComment.text = data.textComment

            ratingBar.numStars = data.note
            ratingBar.rating = data.note.toFloat()

            Glide.with(context)
                .load(data.urlPhoto)
                .apply(RequestOptions.circleCropTransform())
                .into(imgProfile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup) = ViewHolder(parent)
    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int) = item is Comment
    override fun onBindViewHolder(item: Comment, viewHolder: ViewHolder, payloads: MutableList<Any>) = viewHolder.bind(item)

}