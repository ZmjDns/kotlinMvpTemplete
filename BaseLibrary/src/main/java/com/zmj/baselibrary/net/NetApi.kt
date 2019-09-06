package com.zmj.baselibrary.net

import com.zmj.baselibrary.data.entry.BaseResponse
import com.zmj.baselibrary.data.entry.LoginResp
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/9/6
 * Description :
 */
interface NetApi {
    @POST("action=login")
    @FormUrlEncoded
    suspend fun login(@Field("name") name: String,
                      @Field("pass") pass: String): BaseResponse<LoginResp>
}