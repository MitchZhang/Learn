package com.mitch.learnandroid.dispatch

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatSeekBar

/**
 * @Class: MySeekBar
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/7/26
 */
class MySeekBar(context: Context,attributeSet: AttributeSet): AppCompatSeekBar(context,attributeSet) {


    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        Log.e("zmz","onTouchEvent:${event.action}")
//        if(event.action == MotionEvent.ACTION_DOWN){
//            parent.requestDisallowInterceptTouchEvent(true)
//        }
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
//        if(event.action == MotionEvent.ACTION_DOWN){
//            parent.requestDisallowInterceptTouchEvent(true)
//        }
//        Log.e("zmz","onTouchEvent:${event.action}")
        return super.onTouchEvent(event)
    }
}