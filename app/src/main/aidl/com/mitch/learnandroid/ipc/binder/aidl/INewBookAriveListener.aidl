// INewBookAriveListener.aidl
package com.mitch.learnandroid.ipc.binder.aidl;
import com.mitch.learnandroid.ipc.binder.aidl.Book;

// Declare any non-default types here with import statements

interface INewBookAriveListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void onNewBookArrive(in Book book);

}