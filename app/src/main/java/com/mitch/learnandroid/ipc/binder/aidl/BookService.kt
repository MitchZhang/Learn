package com.mitch.learnandroid.ipc.binder.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteCallbackList
import android.util.Log
import com.blankj.utilcode.util.LogUtils
import java.util.concurrent.CopyOnWriteArrayList

/**
 * @Class: BookService
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/5/23
 */
class BookService : Service() {

    private var mBookList: CopyOnWriteArrayList<Book> = CopyOnWriteArrayList()

    //    private var mListeners: CopyOnWriteArrayList<INewBookAriveListener> = CopyOnWriteArrayList()
    //使用RemoteCallbackList代替CopyOnWriteArrayList,用于维护进程间通知
    private var mListeners: RemoteCallbackList<INewBookAriveListener> = RemoteCallbackList()

    private var mBinder = object : IBookManager.Stub() {
        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {

        }

        override fun getBookList(): MutableList<Book> {
            synchronized(this) {
                if (mBookList.isNullOrEmpty()) {
                    return mutableListOf()
                }
                return mBookList
            }
        }

        override fun addBook(book: Book?) {
            synchronized(this) {
                try {
                    if (book != null) {
                        mBookList.add(book)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                Log.e("zmz", "addBook:$book")
            }
        }

        override fun addRandom() {
            synchronized(this) {
                val book = Book(1, "添加的书籍:${System.currentTimeMillis()}")
                mBookList.add(book)
                Log.e("zmz", "addRandom:添加书籍 $book")
            }
        }

        override fun registListener(listener: INewBookAriveListener) {
//            if (!mListeners.contains(listener)) {
//                Log.e("zmz","添加监听  hashCOde：${listener.hashCode()}")
//                mListeners.add(listener)
//            }
            val result = mListeners.register(listener)
            Log.e("zmz", "添加监听  result：$result")
        }

        override fun unRegistListener(listener: INewBookAriveListener): Int {
//            if (mListeners.contains(listener)) {
//                val result = mListeners.remove(listener)
//                Log.e("zmz", "unRegistListener:$result")
//            }
//            return mListeners.size
            mListeners.unregister(listener)
            return mListeners.registeredCallbackCount
        }
    }

    override fun onCreate() {
        super.onCreate()
        val book = Book(1, "Mitch练习,开发艺术探索")
        val book1 = Book(2, "Be More Focus!")
        mBookList.add(book)
        mBookList.add(book1)
        startNewBookThread()
    }

    private fun startNewBookThread() {
        Thread {
            var tag = 1
            while (true) {
                Thread.sleep(5000)
//                mListeners.forEach {
//                    it.onNewBookArrive(Book(tag,"添加新书${System.currentTimeMillis()}"))
//                }
//                tag++
                val count = mListeners.beginBroadcast()
                for (i in 0 until count) {
                    Log.e("zmz","current_index=$i")
                    val listener = mListeners.getBroadcastItem(i)
                    listener.onNewBookArrive(Book(i, "使用RemoteCallBackListener"))
                }
                mListeners.finishBroadcast()
            }
        }.start()
    }

    override fun onBind(p0: Intent?): IBinder {
        return mBinder
    }

}