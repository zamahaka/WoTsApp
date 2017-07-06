package com.zamahaka.wotsapp

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

/**
 * Created by Ura on 07.07.2017.
 */

fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, onChanged: (T?) -> Unit)
        = observe(lifecycleOwner, Observer(onChanged))