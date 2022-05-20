package com.mitch.learnandroid.ipc.binder

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.Parcel
import com.blankj.utilcode.util.LogUtils

/**
 * @Class: GradleService
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/5/18
 */
class GradleService : Service() {

    companion object {
        const val REQUEST_CODE = 0x1
        val SCORE_MAP = mapOf("mitch" to 100, "zmz" to 99, "ymt" to 200)
    }

    private var mBinder = object : Binder() {
        override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {
            if (code == REQUEST_CODE) {
                val name = data.readString()
                val gradle = getGradleByName(name)
                reply?.let {
                    it.writeInt(gradle)
                }
                return true
            }
            return super.onTransact(code, data, reply, flags)
        }

        fun getGradleByName(name: String?): Int {
            var gradle = SCORE_MAP[name]
            if (gradle == null) {
                gradle = -1
            }
            return gradle
        }
    }

    override fun onBind(p0: Intent?): IBinder {
        LogUtils.e("GradleService:onBind")
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        LogUtils.e("GradleService:onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LogUtils.e("GradleService:onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.e("GradleService:onDestroy")
    }
}