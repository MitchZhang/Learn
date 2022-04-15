package com.mitch.learnandroid.bean

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.mitch.learnandroid.BR


/**
 * @Class: ClassField
 * @Description:
 * @author: MitchZhang
 * @Date: 2022/4/13
 */

class User : BaseObservable() {
    @Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @Bindable
    var age: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.age)
        }
}

class User2 {
    var name = ObservableField<String>()
    var age = ObservableInt()
}
