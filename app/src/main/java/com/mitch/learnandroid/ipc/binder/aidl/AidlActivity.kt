package com.mitch.learnandroid.ipc.binder.aidl

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.TextView
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.mitch.learnandroid.R
import java.lang.Exception

/**
 * @Class: AidlActivity
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/5/20
 */
class AidlActivity : Activity() {

    private var mBinder: IBookManager? = null

    private var mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName, p1: IBinder?) {
            LogUtils.e("onServiceConnected")
            mBinder = IBookManager.Stub.asInterface(p1)
            LogUtils.e("添加监听  hashCOde：${mBookArriveListener.hashCode()}")
            mBinder?.registListener(mBookArriveListener)
        }

        override fun onServiceDisconnected(p0: ComponentName) {
            LogUtils.e("onServiceDisconnected")
            mBinder = null
        }
    }

    private var mBookArriveListener = object : INewBookAriveListener.Stub() {
        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {

        }

        override fun onNewBookArrive(book: Book) {
            //要切换到UI线程调用 , 这里是服务器Binder的线程，子线程不能更新UI 需切换到UI线程操作
            LogUtils.e("onNewBookArrive:当前线程${Thread.currentThread().id}")
            LogUtils.e("onNewBookArrive:$book")
//            findViewById<TextView>(R.id.getBookList).text = "会crash吗"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.e("UI线程:${Thread.currentThread().id}")
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
                mBinder?.addBook(book)
                val result = mBinder?.bookList
                LogUtils.e("添加书籍之后:$result")
            } catch (e: Exception) {
                e.printStackTrace()
                LogUtils.e("添加book出错:${e.message}")
            }
        }

        findViewById<TextView>(R.id.removeListener).setOnClickListener {
            //note 移除不了监听，因为就不是一个对象。 夸进程传输，不是同一个对象
            //跨进程传输对象，经过序列化 和 反序列化之后就不是同一个对象，hashCode值也不同
            val result = mBinder?.unRegistListener(mBookArriveListener)
            LogUtils.e("移除监听之后:$result")
        }

    }

    private fun bindService() {
        val intent = Intent("com.mitch.book.service")
        intent.setPackage(packageName)
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.e("onDestroy")
        mBinder?.unRegistListener(mBookArriveListener)
        unbindService(mServiceConnection)
    }


}