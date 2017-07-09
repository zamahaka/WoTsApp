package com.zamahaka.wotsapp

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.zamahaka.wotsapp.data.remote.MyRetrofit
import com.zamahaka.wotsapp.data.remote.model.SearchUser
import com.zamahaka.wotsapp.data.remote.model.UserSearchResponse
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Ura on 03.07.2017.
 */
class SearchUsersViewModel(application: Application) : AndroidViewModel(application) {
    val mUsers = MutableLiveData<List<SearchUser>>()
    val mError = MutableLiveData<Throwable>()

    fun setInput(userName: String) = if (userName.length >= 3) {
        MyRetrofit.wotApi.searchAccounts(userName).enqueue(response = { _, response ->
            val data = response.body()?.data

            data?.let { mUsers.value = it } ?: run { mError.value = Throwable(response.body()?.error.toString()) }

            Log.d("myLog", "onResponse: data set")
        }, failure = { _, throwable ->
            mError.value = throwable

            Log.d("myLog", "onFailure: ")
        })
    } else mError.value = Throwable("Min username length is 3")

    override fun onCleared() {
        Log.d("myLog", "onCleared: ")
    }
}