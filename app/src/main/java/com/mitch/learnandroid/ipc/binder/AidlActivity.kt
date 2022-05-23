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
import com.mitch.learnandroid.ipc.binder.aidl.Book
import com.mitch.learnandroid.ipc.binder.aidl.IBookManager
import java.lang.Exception

/**
 * @Class: AidlActivity
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/5/20
 */
class AidlActivity : Activity() {

    private var mBinder : IBookManager ?= null

    private var mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName, p1: IBinder?) {
            LogUtils.e("onServiceConnected")
            mBinder = IBookManager.Stub.asInterface(p1)
        }

        override fun onServiceDisconnected(p0: ComponentName) {
            LogUtils.e("onServiceDisconnected")
            mBinder = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aidl)
        bindService()
        findViewById<TextView>(R.id.getBookList).setOnClickListener {
            val data = mBinder?.bookList
            data?.let {
                LogUtils.e("获取数据列表：$it")
            }
        }

        findViewById<TextView>(R.id.addBook).setOnClickListener {
            try {
                val book = Book(1, "Mitch:${System.currentTimeMillis()}")
                LogUtils.e("添加Book:${mBinder?.asBinder()?.isBinderAlive}")
//                mBinder?.addBook(book)
                mBinder?.addRandom()
            }catch (e:Exception){
                e.printStackTrace()
                LogUtils.e("添加book出错:${e.message}")
                bindService()
            }
        }
    }

    private fun bindService() {
        val intent = Intent("com.mitch.book.service")
        intent.setPackage(packageName)
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(mServiceConnection)
    }


}