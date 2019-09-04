package com.zmj.baselibrary.basemvp

import android.content.Context

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/9/4
 * Description :
 */
abstract class MvpBasePresenter<V: MvpBaseView> {

    private lateinit var context: Context
    private lateinit var baseView: V

    fun onCreate(baseView: V){
        this.baseView = baseView
        context = baseView.getContext()
    }

    fun showLoading(){
        baseView.showLoading()
    }
}