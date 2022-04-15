package com.mitch.learnandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.mitch.learnandroid.bean.User
import com.mitch.learnandroid.bean.User2
import com.mitch.learnandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val testBean = ObservableField<String>()
    val liveData = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bd = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        var user = User().apply {
            name = "测试name"
            age = 18
        }

        var user2 = User2().apply {
            name.set("123")
            age.set(199)
        }

        bd.data1 = testBean
        bd.data2 = liveData
        bd.user = user
        bd.user2 = user2

        bd.tv1.setOnClickListener {
            user.name = "Mitch"
            user.age = 28

            user2.name.set("M73")
            user2.age.set(125)
            liveData.value = "123"
        }

        bd.tv2.setOnClickListener {
            user.age = 999
            user2.age.set(888)
        }

        bd.button.setOnClickListener {
            liveData.observe(this) {
                LogUtils.e("liveData:$it")
            }
        }

        testBean.set("onCreate")
        liveData.value = "onCreate"
    }

    override fun onStart() {
        super.onStart()
        testBean.set("onStart")
        liveData.value = "onStart"
    }

    override fun onPause() {
        super.onPause()
        testBean.set("onPause")
        liveData.value = "onPause"
    }

    override fun onResume() {
        super.onResume()
        testBean.set("onResume")
        liveData.value = "onResume"
    }

    override fun onDestroy() {
        super.onDestroy()
        liveData.value = "onDestroy"
    }

}