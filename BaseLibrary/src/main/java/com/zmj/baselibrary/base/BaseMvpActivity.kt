package com.zmj.baselibrary.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.annotations.Nullable

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/9/3
 * Description :
 */
abstract class BaseMvpActivity<P: BasePresenter<BaseView>>: AppCompatActivity(),BaseView {

    var presenter: P? = null

    /**
     * 加载布局
     */
    protected abstract fun getLayoutId(): Int

    /**
     * 初始化Presenter
     */
    @Nullable
    protected abstract fun initPresenter(): P

    /**
     * 初始化控件
     */
    protected abstract fun initView()

    /**
     * 绑定数据
     */
    protected abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getLayoutId() > 0){
            setContentView(getLayoutId())
        }

        presenter = initPresenter()
        if (presenter != null){
            presenter!!.onCreate(this)
        }

        initialize()
    }

    /**
     * 加载View和Data
     */
    protected fun initialize() {
        initView()
        initData()
    }

    /**
     * 数据销毁
     */
    override fun onDestroy() {
        if (presenter != null){
            presenter!!.onDestroy()
        }
        super.onDestroy()
    }

    /**
     * 获取当前Activity的上下文
     */
    override fun getContext(): Context {
        return getActivity()
    }

    protected fun getActivity(): Activity{
        return this
    }


}