package com.idan.app.module.common.base

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    var hideStatus = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTranslucentStatus()
        setContentView(layoutResId())
        supportActionBar?.hide()
        initView()
        initData()
    }

    abstract fun layoutResId(): Int

    open fun initView() {}

    abstract fun initData()

    /**
     * 状态栏透明
     */
   open fun setTranslucentStatus() {
        if (!hideStatus)
            return
        //5.0及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = Color.TRANSPARENT
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            //4.4以上
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val layoutParams = window.attributes
            layoutParams.flags =
                layoutParams.flags or WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            window.attributes = layoutParams
        }
    }
}