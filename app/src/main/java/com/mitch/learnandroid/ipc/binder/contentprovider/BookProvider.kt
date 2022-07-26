package com.mitch.learnandroid.ipc.binder.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.util.Log
import java.lang.IllegalArgumentException

/**
 * @Class: BookProvider
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/6/7
 */
class BookProvider : ContentProvider() {

    private var TAG = "BookProvider"

    companion object {
        val AUTHORITY = "com.mitch.learnandroid.ipc.binder.contentprovider.BookProvider"
        val BOOK_CONTENT_URI = Uri.parse("content://$AUTHORITY/book")
        val USER_CONTENT_URI = Uri.parse("content://$AUTHORITY/user")
        val BOOK_URL_CODE = 0
        val USER_URL_CODE = 1
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    }

    init {
        uriMatcher.addURI(AUTHORITY, "book", BOOK_URL_CODE)
        uriMatcher.addURI(AUTHORITY, "user", USER_URL_CODE)
    }

    private lateinit var mDb: SQLiteDatabase

    private fun getTableName(uri: Uri): String {
        var tableName = ""
        when (uriMatcher.match(uri)) {
            BOOK_URL_CODE -> {
                tableName = DBOpenHelper.BOOK_TABLE_NAME
            }

            USER_URL_CODE -> {
                tableName = DBOpenHelper.USER_TABLE_NAME
            }
        }
        return tableName
    }


    override fun onCreate(): Boolean {
        Log.e(TAG, "onCreate")
        initProviderData()
        return false
    }

    private fun initProviderData() {
        mDb = DBOpenHelper(context!!).writableDatabase
        mDb.execSQL("delete from ${DBOpenHelper.BOOK_TABLE_NAME}")
        mDb.execSQL("delete from ${DBOpenHelper.BOOK_TABLE_NAME}")
        mDb.execSQL("insert into book values(1,'Android');")
        mDb.execSQL("insert into book values(2,'iOS');")
        mDb.execSQL("insert into book values(3,'Kotlin');")
        mDb.execSQL("insert into book values(4,'Java');")
        mDb.execSQL("insert into book values(5,'Python');")

        mDb.execSQL("insert into user values(7,'mitch',1);")
        mDb.execSQL("insert into user values(8,'ymt',0);")
    }

    override fun query(
        uri: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        Log.e(TAG, "query:current Thread:${Thread.currentThread().id}")
        val table = getTableName(uri)
        if(table.isNullOrEmpty()){
            throw IllegalArgumentException("UnSupport Uri:$uri")
        }
        return mDb.query(table,p1,p2,p3,null,null,p4,null)
    }

    override fun getType(uri: Uri): String? {
        Log.e(TAG, "getType")
        return null
    }

    override fun insert(uri: Uri, p1: ContentValues?): Uri{
        Log.e(TAG, "insert")
        val tableName = getTableName(uri)
        Log.e(TAG, "tableName:$tableName")
        if(tableName.isNullOrEmpty()){
            throw IllegalArgumentException("UnSupport Uri:$uri")
        }
        mDb.insert(tableName,null,p1)
        context?.contentResolver?.notifyChange(uri,null)
        return uri
    }

    override fun delete(uri: Uri, p1: String?, p2: Array<out String>?): Int {
        Log.e(TAG, "delete")
        val tableName = getTableName(uri)
        if(tableName.isNullOrEmpty()){
            throw IllegalArgumentException("UnSupport Uri:$uri")
        }
        val count = mDb.delete(tableName,p1,p2)
        if(count > 0){
            context?.contentResolver?.notifyChange(uri,null)
        }
        Log.e(TAG, "delete count=$count")
        return count
    }

    override fun update(uri: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        Log.e(TAG, "update")
        val tableName = getTableName(uri)
        if(tableName.isNullOrEmpty()){
            throw IllegalArgumentException("UnSupport Uri:$uri")
        }
        val row = mDb.update(tableName,p1,p2,p3)
        if(row > 0){
            context?.contentResolver?.notifyChange(uri,null)
        }
        Log.e(TAG, "update row=$row")
        return row
    }
}