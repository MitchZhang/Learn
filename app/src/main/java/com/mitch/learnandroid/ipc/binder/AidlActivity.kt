package com.mitch.learnandroid.ipc.binder

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.TextView
import com.blankj.utilcode.util.LogUtils
import com.mitch.learnandroid.R
import com.mitch.learnandroid.ipc.binder.aidl.GradleInterface

/**
 * @Class: AidlActivity
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/5/20
 */
class AidlActivity : Activity() {

    private var mBinder : GradleInterface ?= null

    private var mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName, p1: IBinder?) {
            mBinder = GradleInterface.Stub.asInterface(p1)
        }

        override fun onServiceDisconnected(p0: ComponentName) {
            mBinder = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aidl)
        bindService()
        findViewById<TextView>(R.id.getScore).setOnClickListener {
          val result =  mBinder?.getScore("zmz")
            LogUtils.e("result=$result")
        }
    }

    private fun bindService() {
        val intent = Intent("com.mitch.service")
        intent.setPackage(packageName)
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(mServiceConnection)
    }


}