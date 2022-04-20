package com.mitch.learnandroid.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.mitch.learnandroid.BuildConfig
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @Class: ParamsInterceptor
 * @Description: 默认参数拦截器,用于添加请求的默认参数
 * @author: MitchZhang
 * @Date: 2022/4/20
 */
class ParamsInterceptor : Interceptor {

    val defaultParamsMap = mutableMapOf(
        "xx" to "xxx"
//        "channelId" to "PIDN01PPnFW4",
//        "source" to "2",
//        "register" to "1",
//        "version" to "2.0.8.1",
//        "companyCode" to "NT_001",
//        "lang_area" to "zh_CN",
//        "lang" to "zh_CN",
//        "appCode" to "NT_001",
//        "appSubCode" to "HGT",
//        "vnum" to BuildConfig.VERSION_CODE.toString()
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val newRequest = if ("GET" == originRequest.method) {
            val requestUrl = originRequest.url
            val newUrl = requestUrl.newBuilder()
            defaultParamsMap.forEach { (key, value) ->
                newUrl.addQueryParameter(key, value)
            }
            originRequest.newBuilder().url(newUrl.build()).build()
        } else {
            val postBody = FormBody.Builder()
            defaultParamsMap.forEach { (key, value) ->
                postBody.add(key, value)
            }
            originRequest.newBuilder().post(postBody.build()).build()
        }
        return chain.proceed(newRequest)
    }
}