package com.idan.app.module.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idan.app.module.common.base.BaseFragment
import kotlinx.android.synthetic.main.item_main_list_layout.view.*
import kotlinx.android.synthetic.main.movie_fragment_movie_layout.*
import java.util.ArrayList

class MovieFragment : BaseFragment() {

    var datas: ArrayList<String> = ArrayList()

    override fun layoutId(): Int = R.layout.movie_fragment_movie_layout

    override fun initData() {
        for (i in 1..20) datas.add("Item：$i")
        vp.adapter = BannerAdapter(datas)
        recycler.layoutManager = GridLayoutManager(context, 2)
        recycler.adapter = ContentAdapter(datas)
    }

}

class BannerAdapter(var datas: ArrayList<String>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_main_vp_layout,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.text.text = datas[position]
    }
}


class ContentAdapter(var datas: ArrayList<String>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_main_list_layout,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.text.text = datas[position]
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)



