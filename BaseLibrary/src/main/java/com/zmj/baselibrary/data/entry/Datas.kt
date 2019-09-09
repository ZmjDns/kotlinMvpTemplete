package com.zmj.baselibrary.data.entry

import androidx.lifecycle.MutableLiveData

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * DitHub : https://github.com/ZmjDns
 * Time : 2019/9/6
 * Description :
 */

data class BaseResponse<T>(var code: Int,
                        var message: String,
                        var data: T)

data class LoginResp(var isSuccess: Boolean,
                     var loginMsg: String)