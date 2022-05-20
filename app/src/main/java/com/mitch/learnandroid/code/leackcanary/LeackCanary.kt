package com.mitch.learnandroid.code.leackcanary

import java.lang.ref.Reference
import java.lang.ref.ReferenceQueue
import java.lang.ref.SoftReference
import java.lang.ref.WeakReference

/**
 * @Class: LeackCanary
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/5/11
 */

fun main() {
    //WeakReference ReferenceQueue
    val queue = ReferenceQueue<Any?>()
    var obj: Any? = Any()
    var obj1: Any? = Any()
    var weakRef = WeakReference(obj, queue)
    var weakRef1 = WeakReference(obj1, queue)
    println("GC 回收前:weakRef=$weakRef \n weakRef1=$weakRef1")
//    obj = null
    obj1 = null

    //执行gc
    Runtime.getRuntime().gc()
    Thread.sleep(5000)

    var ref: Reference<Any>?
    do {
        ref = queue.poll() as Reference<Any>?
        println("ref = $ref")
    } while (ref != null)
}

class User(var name: String)

fun test(){
    val queue = ReferenceQueue<Any?>()
    var obj: Any? = Any()
    val ref = SoftReference<Any>(obj,queue)
}