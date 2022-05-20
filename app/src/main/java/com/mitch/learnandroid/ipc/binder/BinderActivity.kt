package com.mitch.learnandroid.ipc.binder

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Parcel
import android.widget.Button
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.mitch.learnandroid.R

/**
 * @Class: BinderActivity
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/5/18
 */
class BinderActivity : Activity() {

    private var mBinder: IBinder? = null

    private var mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName, p1: IBinder?) {
            ToastUtils.showShort("onServiceConnected")
            mBinder = p1
        }

        override fun onServiceDisconnected(p0: ComponentName) {
            ToastUtils.showShort("onServiceDisconnected")
            mBinder = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_binder)
        init()
    }

    private fun init() {
        findViewById<Button>(R.id.bindService).setOnClickListener {
            val intent = Intent("com.mitch.service")
            intent.setPackage(packageName)
            bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
        }

        findViewById<Button>(R.id.startService).setOnClickListener {
            val intent = Intent("com.mitch.service")
            intent.setPackage(packageName)
            startService(intent)
        }

        findViewById<Button>(R.id.queryData).setOnClickListener {
            mBinder?.let {
                val data = Parcel.obtain()
                val replay = Parcel.obtain()
                data.writeString("zmz")
                it.transact(GradleService.REQUEST_CODE, data, replay, 0)
                val score = replay.readInt()
                ToastUtils.showShort("查询到的数据：$score")
                data.recycle()
                replay.recycle()
                LogUtils.e("获取到的score=$score")
            }
        }
    }
}