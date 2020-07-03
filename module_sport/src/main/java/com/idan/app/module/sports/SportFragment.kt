package com.idan.app.module.sports

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idan.app.module.common.base.*
import com.idan.app.module.sports.bean.Sport
import kotlinx.android.synthetic.main.sports_fragment_sports_layout.*
import java.util.ArrayList

class SportFragment : BaseFragment() {

    var datas: ArrayList<Sport> = ArrayList()

    override fun layoutId(): Int = R.layout.sports_fragment_sports_layout

    override fun initData() {
        datas.add(Sport(0, 1, "banner"))

        for (i in 1..6) {
            datas.add(Sport(i, 2, "grid"))
        }
        datas.add(Sport(0, 0, "推荐"))

        for (i in 1..10) {
            datas.add(Sport(i, 3, "list"))
        }

        recycler.layoutManager = GridLayoutManager(context, 3)

        recycler.adapter = SportAdapter(datas)

    }
}

class SportAdapter(var datas: ArrayList<Sport>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int = datas[position].type

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val manager = recyclerView.layoutManager
        if (manager is GridLayoutManager) {
            var spanCount = manager.spanCount
            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (getItemViewType(position)) {
                        2 -> 1 // Grid 时
                        else -> spanCount // 其他类型占一行
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            1 -> BannerHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.sport_item_banner_layout,
                    parent,
                    false
                )
            )
            2 -> GridHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.sport_item_grid_layout,
                    parent,
                    false
                )
            )
            3 -> ListHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.sport_item_list_layout,
                    parent,
                    false
                )
            )
            else -> TitleHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.sport_item_title_layout,
                    parent,
                    false
                )
            )
        }

    override fun getItemCount(): Int = datas.size

}