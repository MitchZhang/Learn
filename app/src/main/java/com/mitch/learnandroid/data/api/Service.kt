package com.mitch.learnandroid.data.api

import com.mitch.learnandroid.data.api.bean.BannerBean
import com.mitch.learnandroid.repository.BaseResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * @Class: Service
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/4/19
 */
interface Service {
    @GET("/wxarticle/chapters/json")
    fun getServerConfig(): Call<BaseResponse<MutableList<BannerBean>>>
}