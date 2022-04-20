package com.mitch.learnandroid.repository

import com.blankj.utilcode.util.LogUtils
import com.mitch.learnandroid.data.api.Api
import com.mitch.learnandroid.data.api.Service
import com.mitch.learnandroid.data.api.bean.BannerBean
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*


/**
 * @Class: DataRepository
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/4/19
 */
object DataRepository {

    private var mRetrofit: Retrofit

    init {
        mRetrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .client(getUnsafeOkHttpClient().build())
            .addConverterFactory(GsonConverterFactory.create())
//            .addConverterFactory()
            .build()
    }

    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
        val loggingInterceptor = HttpLoggingInterceptor()
        val headInterceptor = HeadInterceptor()
        val paramsInterceptor = ParamsInterceptor()
        return try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(
                object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate?>? {
                        return arrayOf()
                    }
                }
            )
            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(8, TimeUnit.SECONDS)
                .readTimeout(8, TimeUnit.SECONDS)
                .writeTimeout(8, TimeUnit.SECONDS)
                .addNetworkInterceptor(headInterceptor)
                .addNetworkInterceptor(paramsInterceptor)
                .addInterceptor(loggingInterceptor)
                .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                .hostnameVerifier(HostnameVerifier { hostname, session -> true })
            builder
        } catch (e: Exception) {
            LogUtils.e("error:${e.message}")
            throw RuntimeException(e)
        }
    }
    private var mConfigCall: Call<BaseResponse<MutableList<BannerBean>>>? = null
    fun getServerConfig(listener: OnResult<BaseResponse<MutableList<BannerBean>>>) {
        mConfigCall = mRetrofit.create(Service::class.java).getServerConfig()
        mConfigCall?.enqueue(object : Callback<BaseResponse<MutableList<BannerBean>>> {
            override fun onResponse(
                call: Call<BaseResponse<MutableList<BannerBean>>>,
                response: Response<BaseResponse<MutableList<BannerBean>>>
            ) {
                LogUtils.e("获取服务器配置:$response \n ${response.body()} ${response.message()}")
                val baseResponse = response.body()
                baseResponse?.let {
                    listener.onResult(it)
                }
            }

            override fun onFailure(call: Call<BaseResponse<MutableList<BannerBean>>>, t: Throwable) {
                listener.onResult(
                    BaseResponse<MutableList<BannerBean>>(null, -1, t.message ?: "request failed")
                )
                LogUtils.e("获取服务器配置失败:${t.message}")
                t.printStackTrace()
            }
        })
    }

    fun stopGetServerConfig() {
        mConfigCall?.let {
            if (!it.isCanceled) {
                it.cancel()
            }
        }
    }
}