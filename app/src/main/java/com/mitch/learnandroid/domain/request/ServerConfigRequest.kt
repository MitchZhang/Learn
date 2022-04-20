package com.mitch.learnandroid.domain.request

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.kunminx.architecture.domain.request.BaseRequest
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import com.mitch.learnandroid.data.api.bean.BannerBean
import com.mitch.learnandroid.repository.BaseResponse
import com.mitch.learnandroid.repository.DataRepository
import com.mitch.learnandroid.repository.OnResult

/**
 * @Class: ServerRequest
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/4/19
 */
class ServerConfigRequest : BaseRequest(), DefaultLifecycleObserver {

    private var mBannerBean: UnPeekLiveData<BaseResponse<MutableList<BannerBean>>> =
        UnPeekLiveData()

    public fun getDictLiveData(): UnPeekLiveData<BaseResponse<MutableList<BannerBean>>> {
        return mBannerBean
    }

    public fun getServerConfig() {
        DataRepository.getServerConfig(object : OnResult<BaseResponse<MutableList<BannerBean>>> {
            override fun onResult(response: BaseResponse<MutableList<BannerBean>>) {
                mBannerBean.value = response
            }
        })
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        DataRepository.stopGetServerConfig()
    }
}