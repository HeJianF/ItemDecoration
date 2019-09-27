package com.example.hjf.itemdecoration

/**
 *
 *
 * @author heJianfeng
 * @date 2019-09-27
 */
object Tools {
    fun dip2px(dpValue: Float): Int {
        val scale = MyApplication.context!!.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}