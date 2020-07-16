package com.idan.app.module.common.base

import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * createUser : Administrator
 * createDate : 2020/7/16
 * remark     : 适配器基类
 */
abstract class BaseAdapter<T : ItemData>(var datas: ArrayList<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int =
        if (datas[position].viewType == -1) super.getItemViewType(position) else datas[position].viewType

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val manager = recyclerView.layoutManager
        if (manager is GridLayoutManager) {
            var spanCount = manager.spanCount
            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return datas[position].viewSpan
                }
            }
        }
    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = createHolder(viewType)

    override fun getItemCount(): Int = datas.size

//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//    }

//    abstract fun createHolder(viewType: Int): RecyclerView.ViewHolder

}