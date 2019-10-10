package com.example.hjf.itemdecoration.decoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.hjf.itemdecoration.utils.Tools

/**
 * 左右添加装饰
 * @author heJianfeng
 * @date 2019-09-28
 */
class LeftAndRightTagDecoration : RecyclerView.ItemDecoration() {
    private var dividerWidth: Float = Tools.dip2px(5)
    private val dividerLeftPaint: Paint = Paint()
    private val dividerRightPaint: Paint = Paint()

    init {
        dividerLeftPaint.color = Color.BLUE
        dividerRightPaint.color = Color.GREEN
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            if (position % 2 == 0) {
                c.drawRect(view.left.toFloat(),
                           view.top.toFloat(),
                           (view.left + dividerWidth),
                           view.bottom.toFloat(),
                           dividerLeftPaint)
            } else {
                c.drawRect((view.right - dividerWidth),
                           view.top.toFloat(),
                           view.right.toFloat(),
                           view.bottom.toFloat(),
                           dividerRightPaint)
            }
        }
    }
}