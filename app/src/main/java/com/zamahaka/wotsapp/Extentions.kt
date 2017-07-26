package com.zamahaka.wotsapp

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ura on 07.07.2017.
 */

fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, onChanged: (T?) -> Unit)
        = observe(lifecycleOwner, Observer(onChanged))


fun <T> Call<T>.enqueue(response: (Call<T>, Response<T>) -> Unit,
                        failure: (Call<T>, Throwable?) -> Unit = { _, _ -> }) =
        enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable?) = failure(call, t)
            override fun onResponse(call: Call<T>, r: Response<T>) = response(call, r)
        })

inline fun <T> Call<T>.enqueue(init: CallbackHolder<T>.() -> Unit) {
    val holder = CallbackHolder<T>()
    holder.init()
    enqueue(holder)
}

class CallbackHolder<T> : Callback<T> {

    var onResponse: ((response: Response<T>) -> Unit)? = null
    var onFailure: ((t: Throwable) -> Unit)? = null

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure?.invoke(t)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onResponse?.invoke(response)
    }
}