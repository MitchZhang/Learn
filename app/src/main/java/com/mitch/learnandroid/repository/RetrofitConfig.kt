package com.mitch.learnandroid.repository

/**
 * @Class: RetrofitConfig
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/4/20
 */
 class BaseResponse<T>(var data: T?, var errorCode: Int,var errorMsg:String){
    var success:Boolean = false
        get() {
        return errorCode == 0
    }

    override fun toString(): String {
        return "BaseResponse(data=$data, errorCode=$errorCode, errorMsg='$errorMsg', success=$success)"
    }

}

interface OnResult<T> {
    fun onResult(response: T)
}