package com.idan.app.module.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idan.app.module.common.base.BaseFragment
import com.idan.app.module.movie.bean.Movie
import com.idan.app.module.movie.detail.MovieDetailActivity
import com.idan.app.module.movie.list.MovieListActivity
import kotlinx.android.synthetic.main.item_main_list_layout.view.*
import kotlinx.android.synthetic.main.item_main_title_layout.view.*
import kotlinx.android.synthetic.main.item_main_vp_layout.view.*
import kotlinx.android.synthetic.main.movie_fragment_movie_layout.*
import java.util.ArrayList

class MovieFragment : BaseFragment() {

    var datas: ArrayList<Movie> = ArrayList()

    override fun layoutId(): Int = R.layout.movie_fragment_movie_layout

    override fun initData() {
        datas.add(Movie(1, "最新"))
        for (i in 1..6) datas.add(Movie(2, "Item：$i"))
        datas.add(Movie(1, "热门"))
        for (i in 1..6) datas.add(Movie(2, "Item：$i"))

        vp.adapter = BannerAdapter(datas)
        recycler.layoutManager = GridLayoutManager(context, 3)
        recycler.adapter = ContentAdapter(datas)
    }
}

class BannerAdapter(var datas: ArrayList<Movie>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_main_vp_layout,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.text.text = datas[position].title
    }
}

class ContentAdapter(var datas: ArrayList<Movie>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val manager = recyclerView.layoutManager
        if (manager is GridLayoutManager) {
            var spanCount = manager.spanCount
            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (getItemViewType(position)) {
                        1 -> spanCount
                        else -> 1
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int = datas[position].type

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            1 -> TitleHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_main_title_layout,
                    parent,
                    false
                )
            )
            else -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_main_list_layout,
                    parent,
                    false
                )
            )
        }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            1 -> bindTitle(holder as TitleHolder, position)
            else -> bindMovie(holder as ViewHolder, position)
        }
    }

    fun bindTitle(holder: TitleHolder, position: Int) {
        holder.itemView.tvTitle.text = datas[position].title

        holder.itemView.tvMore.setOnClickListener {
            MovieListActivity.startAction(it.context)
        }
    }

    fun bindMovie(holder: ViewHolder, position: Int) {
        holder.itemView.movieText.text = datas[position].title

        holder.itemView.setOnClickListener {
            MovieDetailActivity.startAction(it.context)
        }
    }
}


class TitleHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)