package com.mitch.learnandroid.ipc.binder.contentprovider

/**
 * @Class: ProviderUser
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/6/20
 */
class ProviderUser {
    // _id INTEGER PRIMARY KEY,name TEXT,sex INT)
    var id: Int = 0
    var name: String = ""
    var sex: Int = 0 //0女 1=男
    override fun toString(): String {
        return "ProviderUser(id=$id, name='$name', sex=$sex)"
    }
}