package com.mitch.learnandroid.ipc.binder.binderpool

import com.mitch.learnandroid.ICompute

/**
 * @Class: ComeputeImpl
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/6/20
 */
class ComeputeImpl : ICompute.Stub() {
    override fun basicTypes(
        anInt: Int,
        aLong: Long,
        aBoolean: Boolean,
        aFloat: Float,
        aDouble: Double,
        aString: String?
    ) {
    }

    override fun add(a: Int, b: Int): Int {
        return a + b
    }
}