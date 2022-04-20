package com.mitch.learnandroid


import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.mitch.learnandroid.ui.state.MainActivityViewModel


class MainActivity : BaseActivity() {

    private lateinit var mState: MainActivityViewModel

    override fun initViewModel() {
        mState = MainActivityViewModel()
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_main, BR.vm, mState)
    }

}