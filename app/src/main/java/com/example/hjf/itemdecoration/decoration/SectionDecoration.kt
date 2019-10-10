package com.example.hjf.itemdecoration.decoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.hjf.itemdecoration.utils.Tools

/**
 * 插入普通头部装饰
 * @author heJianfeng
 * @date 2019-09-28
 */
class SectionDecoration(private val callback: DecorationCallback) : RecyclerView.ItemDecoration() {

    private val dividerHeight = Tools.dip2px(50)
    private var dividerHeader = Paint()
    private var dividerHeaderText = Paint()

    init {
        dividerHeader.color = Color.BLUE
        dividerHeaderText.color = Color.GREEN
        dividerHeaderText.textSize = Tools.dip2px(30)
        dividerHeaderText.isAntiAlias = true
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val pos = parent.getChildAdapterPosition(view)
        val groupId = callback.getGroupId(pos)
        if (groupId < 0) return
        if (isFirstInGroup(pos)) {
            outRect.top = dividerHeight.toInt()
        } else {
            outRect.top = 0
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            val groupId = callback.getGroupId(position)
            if (groupId < 0) return
            val textLine = callback.getGroupFirstLine(position)
            if (isFirstInGroup(position)) {
                c.drawRect(left.toFloat(),
                           (view.top - dividerHeight),
                           right.toFloat(),
                           view.top.toFloat(),
                           dividerHeader)
                c.drawText(textLine, left.toFloat(), view.top.toFloat(), dividerHeaderText)
            }
        }
    }

    private fun isFirstInGroup(pos: Int): Boolean {
        return if (pos == 0) {
            true
        } else {
            val groupId = callback.getGroupId(pos)
            val prevGroupId = callback.getGroupId(pos - 1)
            prevGroupId != groupId
        }
    }
}