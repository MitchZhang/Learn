package com.mitch.learnandroid.ipc.binder.bundle

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.blankj.utilcode.util.LogUtils
import com.mitch.learnandroid.ipc.binder.aidl.Book

/**
 * @Class: BundleService
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/5/24
 */
class BundleService : Service() {

    override fun onCreate() {
        super.onCreate()
        LogUtils.e("BundleService onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LogUtils.e("BundleService onStartCommand:intent=$intent")
        intent?.let {
            val bundle = it.getBundleExtra("ipc_bundle")
            bundle?.let {
                val name = it.getString("name")
                it.getParcelable<Book>("book")
                val book =  it.getParcelable<Book>("book")
                LogUtils.e("IPC Bundle : name=$name book=$book")
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.e("BundleService onDestroy")
    }
}