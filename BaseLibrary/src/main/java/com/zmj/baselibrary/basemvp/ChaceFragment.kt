package com.zmj.baselibrary.basemvp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.*

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/9/5
 * Description : 主要用于缓存Fragment的布局
 */
abstract class ChaceFragment: Fragment(){

    protected var mRootView: View? = null
    protected var mViewCreated: Boolean = false  //创建View标志位

    private var mCacheView : SparseArray<View>? = null

    abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (getLayoutRes() > 0){
            mRootView = inflater.inflate(getLayoutRes(),container,false)
        }
        mViewCreated = true
        return mRootView
    }

    override fun onDestroyView() {
        mRootView = null
        mViewCreated = false
        super.onDestroyView()
    }

    fun getRootView(): View?{
        return mRootView
    }

    fun <V: View> getView(@IdRes viewId: Int): V{
        if (mCacheView == null){
            mCacheView = SparseArray()
        }
        var view = mCacheView?.get(viewId)
        if (view == null ){
            view = findViewById(viewId)
            if (view != null){
                mCacheView!!.put(viewId,view)
            }
        }

        return view as V
    }

    fun <V: View>findViewById(@IdRes id: Int): V?{
        if (mRootView == null){
            return null
        }
        return mRootView!!.findViewById(id)
    }


}