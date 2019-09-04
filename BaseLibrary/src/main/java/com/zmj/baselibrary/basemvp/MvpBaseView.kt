package com.zmj.baselibrary.basemvp

import android.content.Context

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/9/4
 * Description :
 */
interface MvpBaseView {
    fun getContext(): Context

    fun showLoading()

    fun closeLoading()

    fun clearLoading()
}