package com.mitch.learnandroid.ui.fragment

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.mitch.learnandroid.BR
import com.mitch.learnandroid.R
import com.mitch.learnandroid.ui.state.SplashViewModel

/**
 * @Class: SplashFragment
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/4/19
 */
class SplashFragment : BaseFragment() {

    private lateinit var mState: SplashViewModel

    override fun initViewModel() {
        mState = SplashViewModel()
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_splash, BR.vm, mState)
            .addBindingParam(BR.click, object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    nav().navigate(R.id.goTest)
                }
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(mState.request)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //请求网络,view层持有ViewModel层
        mState.request.getServerConfig()
        //订阅 数据回调,非Mvp接口回调数据
        mState.request.getDictLiveData().observe(viewLifecycleOwner) {
            it?.let {
                //todo 更加优雅的方式 获取list数据
                if (it.success) {
                    ToastUtils.showShort("网络请求成功: 获取到${it.data?.size}条数据 !!!!!!!!!")
                    LogUtils.e("Banner数据:${it.data}")
                } else {
                    ToastUtils.showShort("网络请求失败:${it.errorMsg}")
                }
            }
        }
    }
}