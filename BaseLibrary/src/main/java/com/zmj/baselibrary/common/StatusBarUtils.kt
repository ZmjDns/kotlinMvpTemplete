package com.zmj.baselibrary.common

import android.app.Activity
import android.app.StatusBarManager
import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.viewpager.widget.ViewPager
import com.zmj.baselibrary.view.StatusBarView

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/9/12
 * Description :
 */
/**
 * 设置状态栏颜色
 *
 * @param activity 需要设置的Activity
 * @param color 颜色
 * @param statusBarAlpha 状态栏透明度
 */
fun setTitleLineColor(activity: Activity,@ColorInt color: Int,statusBarAlpha: Int){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        activity.window.statusBarColor = calculateStatusColor(color,statusBarAlpha)
    }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        val decorView = activity.window.decorView as ViewGroup
        val count = decorView.childCount
        if (count > 0 && decorView.getChildAt(count - 1) is StatusBarView){
            decorView.getChildAt(count - 1).setBackgroundColor(calculateStatusColor(color,statusBarAlpha))
        }else{
            val statusBarView = createStstusBarView(activity,color,statusBarAlpha)
            decorView.addView(statusBarView)
        }
        setRootView(activity)
    }
}

/**
 * 设置跟布局参数
 */
fun setRootView(activity: Activity){
    val rootView = activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ViewGroup
    rootView.fitsSystemWindows = true
    rootView.clipToPadding = true
}

/**
 * 创建一个和状态栏大小相同的半透明矩形框
 *
 *@param activity 需要设置的Activity
 * @param color 颜色
 * @param statusBarAlpha 状态栏透明度
 *
 */
fun createStstusBarView(activity: Activity,@ColorInt color: Int,alpha: Int): StatusBarView{
    val statusBarView = StatusBarView(activity)
    val params =LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity))
    statusBarView.layoutParams = params
    statusBarView.setBackgroundColor(calculateStatusColor(color,alpha))
    return statusBarView
}

fun getStatusBarHeight(context: Context): Int{
    val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    return context.resources.getDimensionPixelSize(resourceId)
}

/**
 * 计算状态栏颜色
 *
 *
 */
fun calculateStatusColor(@ColorInt color: Int,alpha: Int): Int{
    val a = 1 - alpha / 255f
    var red = color shr 16 and 0xff
    var green = color shr 8 and 0xff
    var blue = color and 0xff
    red = (red * a + 0.5).toInt()
    green = (green * a + 0.5).toInt()
    blue = (blue * a + 0.5).toInt()
    return 0xff shl 24 or (red shl 16) or (green shl 8) or blue
}