package com.zamahaka.wotsapp

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Yura Stetsyc on 7/26/17.
 */
class CallbackHolder<T> : Callback<T> {

    private var onResponse: ((response: Response<T>) -> Unit)? = null
    private var onFailure: ((t: Throwable) -> Unit)? = null

    fun onResponse(op: (Response<T>) -> Unit) {
        onResponse = op
    }

    fun onFailure(op: (Throwable) -> Unit) {
        onFailure = op
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onResponse?.invoke(response)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure?.invoke(t)
    }
}