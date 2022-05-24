package com.mitch.learnandroid.ipc.binder.messenger

import android.app.Service
import android.content.Intent
import android.os.*
import com.blankj.utilcode.util.LogUtils
import com.mitch.learnandroid.ipc.binder.aidl.Book

/**
 * @Class: MessengerService
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/5/24
 */
class MessengerService : Service() {

    private var mMessengerHandle = object : Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> {
                    //服务器处理数据
                    msg.data?.let {
                        //如果不添加 类加载器 直接报错
                        it.classLoader = Book::class.java.classLoader
                        val book = it.getParcelable<Book>("book")
                        val name = it.getString("name")
                        LogUtils.e("服务器收到数据:name=$name book=$book")
                        val replayMessage = Message.obtain(null, 2)
                        val bundle = Bundle()
                        bundle.putString("replay", "服务器稍后会回复你的哟")
                        replayMessage.data = bundle
                        msg.replyTo?.send(replayMessage)
                    }
                }

            }
        }
    }

    private var mMessenger: Messenger = Messenger(mMessengerHandle)

    override fun onBind(p0: Intent?): IBinder? {
        return mMessenger.binder
    }
}