package com.mitch.learnandroid.ipc.binder.binderpool

import com.blankj.utilcode.util.EncryptUtils
import com.mitch.learnandroid.ISecurityCenter

/**
 * @Class: ScurityCenterImpl
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/6/20
 */
class SecurityCenterImpl : ISecurityCenter.Stub() {

    private val SECRET_CODE = "^-^".toByteArray()

    override fun basicTypes(
        anInt: Int,
        aLong: Long,
        aBoolean: Boolean,
        aFloat: Float,
        aDouble: Double,
        aString: String?
    ) {

    }

    override fun encrypt(content: String): String {
        return "$content[encrypt]"
    }

    override fun decrypt(pasword: String): String {
        return pasword.replace("[encrypt]", "")
    }
}