package com.example.hjf.itemdecoration.decoration

/**
 *
 * @author heJianfeng
 * @date 2019-09-27
 */
interface DecorationCallback {
    fun getGroupId(position: Int): Long
    fun getGroupFirstLine(position: Int): String
}