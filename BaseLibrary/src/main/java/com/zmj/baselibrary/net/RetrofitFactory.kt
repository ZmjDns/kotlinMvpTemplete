package com.zmj.baselibrary.net

import com.zmj.baselibrary.common.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/9/6
 * Description :
 */
class RetrofitFactory {
    companion object{
        val instance: RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val retrofit: Retrofit
    private val interceptor: Interceptor

    init{
        interceptor = Interceptor {
                chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Content-Ttype","application/json")
                .addHeader("charset","utf-8")
                .build()
            chain.proceed(request)
        }


        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //数据类型转换器
            //.addCallAdapterFactory()      //网络请求转换器
            .client(initOkClient())
            .build()
    }

    private fun initOkClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)     //header相关拦截器    也可以添加日志相关拦截器
            .connectTimeout(10,TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .build()
    }

    //请求接口创建
    fun <T> create(apiCls: Class<T>): T{
        return retrofit.create(apiCls)
    }
}