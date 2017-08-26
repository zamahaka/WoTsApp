package com.zamahaka.wotsapp

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.zamahaka.wotsapp.data.remote.Url
import com.zamahaka.wotsapp.mvp.ActiveView
import com.zamahaka.wotsapp.mvp.BasePresenter
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

fun ImageView.load(url: Url) = Picasso.with(context).load(url).fit().centerInside().into(this)

val ActiveView?.isValid: Boolean get() = this?.isActive ?: false

inline fun <V : ActiveView> BasePresenter<V>.performViewOperation(operation: V.() -> Unit) {
    if (view.isValid) view?.operation()
}