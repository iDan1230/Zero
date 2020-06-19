package com.idan.app.module.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    internal var tView: View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (tView === null) {
            tView = inflater.inflate(layoutId(), container)
        }
        tView.let {
            (it as ViewGroup).removeView(tView)
        }
        return tView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    abstract fun layoutId(): Int

    open fun initView() {}

    abstract fun initData()

}