package com.idan.app.module.movie.list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idan.app.module.common.base.BaseActivity
import com.idan.app.module.movie.R
import com.idan.app.module.movie.bean.Movie
import kotlinx.android.synthetic.main.movie_activity_movie_list_layout.*

/**
 * createUser : Administrator
 * createDate : 2020/6/19
 * remark     :
 */
class MovieListActivity : BaseActivity() {

    companion object{
        fun startAction(ct: Context){
            ct.startActivity(Intent(ct,MovieListActivity::class.java))
        }
    }
    val datas:ArrayList<Movie> = ArrayList()

    override fun setTranslucentStatus() {
        hideStatus = false
        super.setTranslucentStatus()
    }

    override fun layoutResId(): Int = R.layout.movie_activity_movie_list_layout



    override fun initData() {
        for (i in 1..100){
            datas.add(Movie(2,"sdfdfd"))
        }
        recycler.layoutManager = GridLayoutManager(this,3)
        recycler.adapter = MovieListAdapter(datas)
    }
}

class MovieListAdapter(val datas:ArrayList<Movie>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main_list_layout,parent,false))

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
