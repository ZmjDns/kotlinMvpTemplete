package com.zmj.baselibrary.common

import android.util.Log
import kotlinx.coroutines.*
import java.lang.Exception

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/9/6
 * Description :
 */
/**
 * 执行网络请求，详解版
 */
fun <T>executeNetRequest(request: suspend () -> T?,         //执行耗时操作，并返回T泛型
                         onSuccess:(T) -> Unit = {},        //耗时完成成功获取返回值T，进行操作
                         onFailed:(Throwable) -> Unit = {}  //耗时完成失败结果，获取throwable，进行操作
                         ): Job{
    val uiScope = CoroutineScope(Dispatchers.Main)  //主线程的CoroutineScope
    return uiScope.launch {
        try {
            val res: T? = withContext(Dispatchers.IO){ request() }  //IO线程中执行网络请求，成功后返回这里继续执行
            res?.let {
                onSuccess(it)
            }
        }catch (e: Exception){
            onFailed(e)
        }
    }
}

/**
 * 执行网络请求
 */
fun <T>executeRequest(request: suspend () -> T?,onSuccess:(T) -> Unit = {},onFailed:(Throwable) -> Unit = {}): Job {
    val uiScope = CoroutineScope(Dispatchers.Main)  //Ui主线程的CoroutineScope
    return uiScope.launch {
        try {
            val res: T? = withContext(Dispatchers.IO) {request()}   //IO线程中执行网络请求，成功后返回这里继续执行
            res?.let {
                onSuccess(it)
            }
        }catch (e: CancellationException){
            Log.e("executeRequest","job canceled")
        }catch (e: Exception){
            Log.e("executeRequest","${e.printStackTrace()}")
            onFailed(e)
        }
    }
}