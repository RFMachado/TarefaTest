package com.exercicio.tarefa.util.shared

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.exercicio.tarefa.util.Util

class ItemDecoration(drawable: Drawable) : RecyclerView.ItemDecoration() {

        private var mDivider: Drawable? = null
        private val mBounds = Rect()

        init {
            setDrawable(drawable)
        }

        fun setDrawable(drawable: Drawable) {
            if (drawable == null) {
                throw IllegalArgumentException("Drawable cannot be null.")
            }
            mDivider = drawable
        }

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            if (parent.layoutManager == null) {
                return
            }

            drawVertical(c, parent)
        }


        private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
            canvas.save()
            val margin = Util.dpToPx(parent.context, 15F)
            val left = margin
            val right = parent.width - margin

            val childCount = parent.childCount

            for (i in 0 until childCount - 1) {
                val child = parent.getChildAt(i)
                if (child.tag != null && child.tag == "no_divider")
                    continue

                if (i + 1 < childCount) {
                    val nextChild = parent.getChildAt(i + 1)
                    if (nextChild.tag != null && nextChild.tag == "no_divider")
                        continue
                }

                parent.getDecoratedBoundsWithMargins(child, mBounds)
                val bottom = mBounds.bottom + Math.round(ViewCompat.getTranslationY(child))
                val top = bottom - mDivider!!.intrinsicHeight
                mDivider!!.setBounds(left, top, right, bottom)
                mDivider!!.draw(canvas)
            }
            canvas.restore()
        }

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            if (view.tag == null || view.tag != "no_divider")
                outRect.set(0, 0, 0, mDivider!!.intrinsicHeight)
        }

    }