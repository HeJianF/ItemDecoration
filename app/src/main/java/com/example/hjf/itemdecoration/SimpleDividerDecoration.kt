package com.example.hjf.itemdecoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author heJianfeng
 * @date 2019-09-27
 */
class SimpleDividerDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private var dividerHeight: Int = Tools.dip2px(5.0f)
    private val dividerPaint: Paint = Paint()

    init {
        dividerPaint.color = context.resources.getColor(R.color.colorAccent)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = dividerHeight
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            c.drawRect(
                left.toFloat(),
                view.bottom.toFloat(), right.toFloat(),
                (view.bottom + dividerHeight).toFloat(), dividerPaint
            )
        }
    }

}