package com.example.hjf.itemdecoration.utils

import com.example.hjf.itemdecoration.MyApplication

/**
 * @author heJianfeng
 * @date 2019-09-27
 */
object Tools {
    fun dip2px(dpValue: Int): Float {
        val scale = MyApplication.context!!.resources.displayMetrics.density
        return (dpValue * scale + 0.5f)
    }
}