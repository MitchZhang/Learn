package com.mitch.learnandroid.dispatch

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.mitch.learnandroid.R

/**
 * @Class: TouchEventActivity
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/7/26
 */
class TouchEventActivity:Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.touch_activity)
        findViewById<View>(R.id.b1).setOnClickListener {
            ToastUtils.showShort("xxxxxxxxxxxxxxxxxxx")
        }
        findViewById<View>(R.id.seekBar).setOnTouchListener { v, event ->
            v.parent.requestDisallowInterceptTouchEvent(true)
            false
        }
    }
}