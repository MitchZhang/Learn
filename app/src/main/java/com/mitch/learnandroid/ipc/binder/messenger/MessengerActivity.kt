package com.mitch.learnandroid.ipc.binder.messenger

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.mitch.learnandroid.R
import com.mitch.learnandroid.ipc.binder.aidl.Book

/**
 * @Class: MessengerActivity
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/5/24
 */
class MessengerActivity : Activity() {

    private var mMessenger: Messenger? = null

    private var replyMessenger: Messenger = Messenger(object : Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                2 -> {
                    msg.data?.let {
                       val serverMessage = it.getString("replay")
                        LogUtils.e("Server:$serverMessage")
                    }
                }
            }
        }
    })

    private var mServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(p0: ComponentName, p1: IBinder?) {
            mMessenger = Messenger(p1)
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            mMessenger = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messenger)
        bindService(
            Intent("com.mitch.messenger.service").apply { setPackage(packageName) },
            mServiceConnection,
            BIND_AUTO_CREATE
        )
        findViewById<View>(R.id.send).setOnClickListener {
            val message = Message.obtain(null, 1)
            message.replyTo = replyMessenger
            val bundle = Bundle()
            bundle.putString("name", "Messenger")
            bundle.putParcelable("book", Book(1, "Messenger of AIDL"))
            message.data = bundle
            mMessenger?.send(message)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(mServiceConnection)
    }
}