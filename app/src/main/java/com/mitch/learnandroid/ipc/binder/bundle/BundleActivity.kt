package com.mitch.learnandroid.ipc.binder.bundle

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.mitch.learnandroid.R
import com.mitch.learnandroid.ipc.binder.aidl.Book

/**
 * @Class: BundleActivity
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/5/24
 */
class BundleActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // startService 流程 onCreate  onStartCommend onDestroy
        // bindService 流程 onCreate onBind onUnBind onDestroy
        setContentView(R.layout.activity_bundle)
        findViewById<TextView>(R.id.send).setOnClickListener {
            val intent = Intent("com.mitch.bundle.service")
            intent.setPackage(packageName)
            val bundle = Bundle()
            bundle.putString("name", "mitch")
            bundle.putParcelable("book", Book(1, "Believe YourSelf"))
            intent.putExtra("ipc_bundle", bundle)
            startService(intent)
        }

        findViewById<TextView>(R.id.close).setOnClickListener {
            val intent = Intent("com.mitch.bundle.service")
            intent.setPackage(packageName)
            stopService(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}