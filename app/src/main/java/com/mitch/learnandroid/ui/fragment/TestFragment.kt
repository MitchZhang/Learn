package com.mitch.learnandroid.ui.fragment

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.mitch.learnandroid.BR
import com.mitch.learnandroid.R
import com.mitch.learnandroid.ipc.binder.AidlActivity
import com.mitch.learnandroid.ipc.binder.BinderActivity

/**
 * @Class: TestFragment
 * @Description: 各种测试
 * @author: MitchZhang
 * @Date: 2022/5/19
 */
class TestFragment : BaseFragment() {

    lateinit var mViewModel:ViewModel

    override fun initViewModel() {
        mViewModel = TestViewModel()
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.test_fragment,BR.testVM,mViewModel).addBindingParam(BR.click,ClickProxy())
    }


    inner class ClickProxy{
        fun toBinderTest(){
            startActivity(Intent(activity,BinderActivity::class.java))
        }

        fun toAidl(){
            startActivity(Intent(activity,AidlActivity::class.java))
        }
    }

    class TestViewModel:ViewModel()
}