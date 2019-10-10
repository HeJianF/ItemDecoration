package com.example.hjf.itemdecoration.decoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.hjf.itemdecoration.utils.Tools
import java.util.*
import kotlin.math.max

/**
 * 黏性头部
 * @author heJianfeng
 * @date 2019-10-10
 */
class PinnedSectionDecoration(private val callback: DecorationCallback) : RecyclerView.ItemDecoration() {

    private val dividerHeight = Tools.dip2px(50)
    private var dividerHeader = Paint()
    private var dividerHeaderText = Paint()

    init {
        dividerHeader.color = Color.RED
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

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val itemCount = state.itemCount
        val childCount = parent.childCount
        val left: Float = parent.paddingLeft.toFloat()
        val right: Float = (parent.width - parent.paddingRight).toFloat()
        var preGroupId: Int
        var groupId = -1
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            preGroupId = groupId
            groupId = callback.getGroupId(position).toInt()
            if (groupId < 0 || preGroupId == groupId) continue
            val textLine = callback.getGroupFirstLine(position).toUpperCase(Locale.CHINA)
            if (TextUtils.isEmpty(textLine)) continue
            val viewBottom = view.bottom
            var textY = max(dividerHeight, view.top.toFloat())
            if (position + 1 < itemCount) {
                val nextGroupId = callback.getGroupId(position + 1)
                if (nextGroupId != groupId.toLong() && viewBottom < textY) {
                    textY = viewBottom.toFloat()
                }
            }
            c.drawRect(left, textY - dividerHeight, right, textY, dividerHeader)
            c.drawText(textLine, left, textY, dividerHeaderText)
        }
    }

    private fun isFirstInGroup(pos: Int): Boolean {
        return if (pos == 0) {
            true
        } else {
            val groupId = callback.getGroupId(pos)
            val prevGroupId = callback.getGroupId(pos - 1)
            return prevGroupId != groupId
        }
    }

}