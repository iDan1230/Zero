package com.idan.app.module.movie.detail

import android.content.Context
import android.content.Intent
import com.idan.app.module.common.base.BaseActivity
import com.idan.app.module.movie.R

class MovieDetailActivity : BaseActivity() {

    companion object{
        open  fun startAction(ct: Context){
            ct.startActivity(Intent(ct, MovieDetailActivity::class.java))
        }
    }

    override fun layoutResId(): Int = R.layout.movie_activity_movie_detail_layout

    override fun initData() {

    }
}
