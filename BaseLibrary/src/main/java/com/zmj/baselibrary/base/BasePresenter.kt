package com.zmj.baselibrary.base

import android.content.Context

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/9/3
 * Description :
 */
abstract class BasePresenter<V: BaseView> {
    private var context: Context? = null
    private var baseView: V? = null

    fun onCreate(baseView: V){
        this.baseView = baseView
        context = baseView.getContext()
    }

    fun onDestroy(){
        context = null
        baseView = null
    }

    fun showLoadingDialog(){
        if (baseView != null) baseView!!.showLoadingDialog()
    }

    fun showLoadingBar(){
        if (baseView != null) baseView!!.showLoadingBar()
    }

    fun dismissDialog(){
        if (baseView != null) baseView!!.dismissDialog()
    }

    fun dismissBar(){
        if (baseView != null) baseView!!.dismissBar()
    }


}