package com.mitch.learnandroid.ui.state

import androidx.lifecycle.ViewModel
import com.mitch.learnandroid.domain.request.ServerConfigRequest

/**
 * @Class: SplashViewModel
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/4/19
 */
class SplashViewModel : ViewModel() {
    val request = ServerConfigRequest()
}