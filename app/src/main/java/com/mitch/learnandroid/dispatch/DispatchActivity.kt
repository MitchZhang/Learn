package com.mitch.learnandroid.dispatch

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import com.blankj.utilcode.util.ToastUtils
import com.mitch.learnandroid.R
import com.mitch.learnandroid.tools.EventTools

/**
 * @Class: DispatchActivity
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/7/20
 */
class DispatchActivity : Activity() {

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var viewGroup: MyLinearLayout

    // 事件分发顺序: Activity->Window(PhoneWindow)->DecorView(setContentView设置的view)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dispatch_layout)
        btn2 = findViewById<Button>(R.id.btn2)
        btn1 = findViewById<Button>(R.id.btn1)
        viewGroup = findViewById<MyLinearLayout>(R.id.ll)
        btn1.setOnClickListener {
            ToastUtils.showShort("Btn1 Click")
        }
        btn2.setOnClickListener {
            ToastUtils.showShort("Btn2 Click")
            viewGroup.interceptEvent = true
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        Log.e("DispatchActivity", "dispatchTouchEvent:${EventTools.getEventStr(ev.action)}")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.e("DispatchActivity", "onTouchEvent:${EventTools.getEventStr(event.action)}")
        return super.onTouchEvent(event)
    }

}