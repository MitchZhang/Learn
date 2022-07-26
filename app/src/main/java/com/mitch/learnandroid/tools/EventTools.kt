package com.mitch.learnandroid.tools


/**
 * @Class: EventTools
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/7/20
 */
object EventTools {
    fun getEventStr( eventStatus: Int):String {
        var str = ""
        when (eventStatus) {
            0 -> {
                str = "ACTION_DOWN"
            }
            1 -> {
                str = "ACTION_UP"
            }
            2 -> {
                str = "ACTION_MOVE"
            }
            3 -> {
                str = "ACTION_CANCEL"
            }
        }
        return str
    }
}