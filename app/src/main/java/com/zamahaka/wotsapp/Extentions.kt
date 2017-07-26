package com.zamahaka.wotsapp

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import retrofit2.Call

/**
 * Created by Ura on 07.07.2017.
 */

fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, onChanged: (T?) -> Unit)
        = observe(lifecycleOwner, Observer(onChanged))

inline fun <T> Call<T>.enqueue(init: CallbackHolder<T>.() -> Unit) {
    val holder = CallbackHolder<T>()
    holder.init()
    enqueue(holder)
}