package com.mitch.learnandroid.dispatch

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout
import com.mitch.learnandroid.tools.EventTools

/**
 * @Class: MyLinearLayout
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/7/21
 */
class MyLinearLayout(var mContext: Context, var attributes: AttributeSet) :
    LinearLayout(mContext, attributes) {

    var interceptEvent = false

    //ViewGroup的事件分发
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        Log.e("MyLinearLayout","dispatchTouchEvent:${EventTools.getEventStr(ev.action)}")
        return super.dispatchTouchEvent(ev)
    }

    //如果ViewGroup的子View不拦截事件，那么由ViewGroup的onTouchEvent处理事件
    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.e("MyLinearLayout","onTouchEvent:${EventTools.getEventStr(event.action)}")
        return super.onTouchEvent(event)
//        return interceptEvent
    }


    //标识ViewGroup是否拦截 点击事件
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        Log.e("MyLinearLayout","onInterceptTouchEvent:${EventTools.getEventStr(ev.action)}")
        return super.onInterceptTouchEvent(ev)
//        return interceptEvent
    }

}