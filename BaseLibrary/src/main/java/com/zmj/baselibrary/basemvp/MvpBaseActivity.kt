package com.zmj.baselibrary.basemvp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.annotations.Nullable

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/9/4
 * Description :
 */
abstract class MvpBaseActivity<in V: MvpBaseView, P: MvpBasePresenter<V>>:AppCompatActivity(),MvpBaseView{

    var mPresenter: P? = null

    @Nullable
    protected abstract fun initPresenter(): P?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter = initPresenter()
        if (mPresenter != null) {
            mPresenter!!.onCreate(this as V)
        }
    }

    override fun getNowContext(): Context {
        return getActivity()
    }

    protected fun getActivity(): Activity{
        return this
    }
}