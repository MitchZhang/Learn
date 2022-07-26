package com.mitch.learnandroid.ui.fragment

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.kunminx.architecture.ui.page.BaseFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.mitch.learnandroid.BR
import com.mitch.learnandroid.R
import com.mitch.learnandroid.dispatch.DispatchActivity

/**
 * @Class: DispatchFragment
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/7/20
 */
class DispatchFragment : BaseFragment() {

    private lateinit var mViewModel: DispatchViewModel

    override fun initViewModel() {
        mViewModel = DispatchViewModel()
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.dispatch_fragment, BR.dispatchVM, mViewModel).addBindingParam(BR.click,ClickProxy())
    }

    class DispatchViewModel : ViewModel()

    inner class ClickProxy {
        fun toDispatchActivity() {
            context!!.startActivity(Intent(activity,DispatchActivity::class.java))
        }
    }
}