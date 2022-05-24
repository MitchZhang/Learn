package com.mitch.learnandroid.ipc.binder.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
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
            LogUtils.e("addBook:$book")
            synchronized(this) {
                try {
                    if (book == null) {
                        LogUtils.e("Client 传过来的数据为空")
                    } else {
                        mBookList.add(book)
                        LogUtils.e("收到Client数据:$book")
                    }
                }catch (e:Exception){
                    LogUtils.e("addBook: Exception,err=${e.message}")
                    e.printStackTrace()
                }
            }
        }

        override fun addRandom() {
            LogUtils.e("addRandom:start")
            val book = Book(1, "Random:${System.currentTimeMillis()}")
            mBookList.add(book)
            LogUtils.e("addRandom:$book")
        }


    }

    override fun onCreate() {
        super.onCreate()
        val book = Book(1, "Mitch练习,开发艺术探索")
        val book1 = Book(2, "Be More Focus!")
        mBookList.add(book)
        mBookList.add(book1)
    }

    override fun onBind(p0: Intent?): IBinder {
        return mBinder
    }

}