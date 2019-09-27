package com.example.hjf.itemdecoration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DecorationCallback {

    private var adapter: DecorationAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = DecorationAdapter(this)
        recycle_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycle_view.adapter = adapter

        recycle_view.addItemDecoration(SimpleDividerDecoration(this))

    }

    override fun getGroupId(position: Int): Long {
        return adapter!!.data[position][0].toLong()
    }

    override fun getGroupFirstLine(position: Int): String {
        return adapter!!.data[position].substring(0, 1)
    }

}
