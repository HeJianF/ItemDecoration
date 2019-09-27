package com.example.hjf.itemdecoration

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @author heJianfeng
 * @date 2019-09-27
 */
class DecorationAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data: Array<String> = context.resources.getStringArray(R.array.animals)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.decoration_item, parent, false)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val textView = holder.itemView as TextView
        textView.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

}