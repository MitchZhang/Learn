// IBookManager.aidl
package com.mitch.learnandroid.ipc.binder.aidl;
// Declare any non-default types here with import statements
import com.mitch.learnandroid.ipc.binder.aidl.Book;
import com.mitch.learnandroid.ipc.binder.aidl.INewBookAriveListener;
interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

   List<Book> getBookList();

  void addBook(in Book book);

  void addRandom();

  void registListener(INewBookAriveListener listener);

  int unRegistListener(INewBookAriveListener listener);

}