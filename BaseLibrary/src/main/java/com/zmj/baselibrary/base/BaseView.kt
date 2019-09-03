package com.zmj.baselibrary.base

import android.content.Context

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/9/3
 * Description :
 */
interface BaseView {
    fun getContext(): Context

    fun showLoadingDialog()

    fun showLoadingBar()

    fun dismissDialog()

    fun dismissBar()

    fun clearLoading()
}