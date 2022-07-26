package com.mitch.learnandroid.dispatch

import android.app.Activity
import android.content.Intent
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
class DispatchActivity : Activity() ,View.OnClickListener{

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button

    // 事件分发顺序: Activity->Window(PhoneWindow)->DecorView(setContentView设置的view)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dispatch_layout)
        initView()
    }

    private fun initView() {
        btn1 = findViewById<Button>(R.id.btn1)
        btn2 = findViewById<Button>(R.id.btn2)
        btn3 = findViewById<Button>(R.id.btn3)

        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn1 ->{

            }
            R.id.btn2 ->{

            }
            R.id.btn3 ->{

            }
        }
    }
}