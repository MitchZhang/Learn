package com.mitch.learnandroid.repository
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @Class: HeadInterceptor
 * @Description: 请求头拦截器
 * @author: MitchZhang
 * @Date: 2022/4/19
 */
class HeadInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val newRequest = originRequest.newBuilder()
            .addHeader("authorization", "")
            .addHeader("uid", "")
            .method(originRequest.method,originRequest.body)
            .build()

        return chain.proceed(newRequest)
    }
}