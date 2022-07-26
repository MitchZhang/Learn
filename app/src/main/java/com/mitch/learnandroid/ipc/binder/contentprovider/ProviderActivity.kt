package com.mitch.learnandroid.ipc.binder.contentprovider

import android.app.Activity
import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import com.blankj.utilcode.util.LogUtils
import com.mitch.learnandroid.R
import com.mitch.learnandroid.code.leackcanary.User
import com.mitch.learnandroid.ipc.binder.aidl.Book

/**
 * @Class: ProviderActivitry
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/6/7
 */
class ProviderActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider)

        findViewById<TextView>(R.id.insert).setOnClickListener {
            val uri = BookProvider.BOOK_CONTENT_URI
            val testUri = Uri.parse("content://com.mitch.learnandroid.ipc.binder.contentprovider.BookProvider/book")
            val contentValue = ContentValues()
            contentValue.put("_id",6)
            contentValue.put("name","Mitch IPC")
            val resultUri = contentResolver.insert(testUri, contentValue)
            LogUtils.e("resultUri=$resultUri")
        }

        findViewById<TextView>(R.id.query).setOnClickListener {
            val cursor =  contentResolver.query(BookProvider.BOOK_CONTENT_URI, arrayOf("_id","name"),null,null,null)
            cursor?.let {
                while (it.moveToNext()){
                    val book = Book()
                    book.bookId = it.getInt(0)
                    book.bookName = it.getString(1)
                    LogUtils.e("query book:$book")
                }
            }
            cursor?.close()
        }

        findViewById<TextView>(R.id.queryUser).setOnClickListener {
            val cursor =  contentResolver.query(BookProvider.USER_CONTENT_URI, arrayOf("_id","name","sex"),null,null,null)
            cursor?.let {
                while (it.moveToNext()){
                    val user = ProviderUser()
                    user.id = it.getInt(0)
                    user.name = it.getString(1)
                    user.sex = it.getInt(2)
                    LogUtils.e("query user:$user")
                }
            }
            cursor?.close()
        }
    }
}