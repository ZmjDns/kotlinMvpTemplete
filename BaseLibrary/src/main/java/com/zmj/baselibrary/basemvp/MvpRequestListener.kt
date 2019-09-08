package com.zmj.baselibrary.basemvp

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/9/6
 * Description : Module请求规则
 */
interface MvpRequestListener<T> {
    fun onStart()

    fun onSuccess(data: T)

    fun onFailed(throwable: Throwable)

    fun onFinish()
}