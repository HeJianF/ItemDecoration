package com.example.hjf.itemdecoration

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * @author heJianfeng
 * @date 2019-09-27
 */
@SuppressLint("StaticFieldLeak")
class MyApplication : Application() {

    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}