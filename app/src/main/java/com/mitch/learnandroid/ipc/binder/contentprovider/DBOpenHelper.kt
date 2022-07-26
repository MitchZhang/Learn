package com.mitch.learnandroid.ipc.binder.contentprovider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * @Class: DBOpenHelper
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/6/7
 */
class DBOpenHelper : SQLiteOpenHelper {

    companion object {
        val DB_NAME = "book_provider.db"
        var DB_VERSION = 1
        val BOOK_TABLE_NAME = "book"
        val USER_TABLE_NAME = "user"
        val CREATE_BOOK_TABLE =
            "CREATE TABLE IF NOT EXISTS $BOOK_TABLE_NAME (_id INTEGER,name TEXT)"
        val CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS $USER_TABLE_NAME (_id INTEGER,name TEXT,sex INT)"
    }

    constructor(context: Context) : super(context, DB_NAME, null, DB_VERSION) {

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_BOOK_TABLE)
        db?.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}