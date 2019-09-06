package com.zmj.baselibrary.basemvp

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/9/5
 * Description :
 */
abstract class MvpFragment< V: MvpBaseView,P: MvpBasePresenter<V>>: LazyFragment(),MvpBaseView {

    protected var presenter: P? = null

    /**
     * 加载布局
     */
    //在父类中声明过此方法，故只能重写
    //protected abstract fun getLayoutRes(): Int

    /**
     * 实例Presenter
     */
    protected abstract fun initPresenter(): P?

    /**
     * 加载控件
     */
    protected abstract fun initView()

    /**
     * 加载数据
     */
    protected abstract fun loadData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = initPresenter()
        if (presenter != null){
            presenter!!.onCreate(this as V)
        }
        initialize()
    }

    protected fun initialize(){
        initView()
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun getNowContext(): Context {
        return activity!!
    }

    fun getFragment(): Fragment{
        return this
    }

}