package com.zamahaka.wotsapp

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.persistence.room.Room
import android.os.Handler
import android.util.Log
import com.zamahaka.wotsapp.data.local.SearchUsersDatabase
import com.zamahaka.wotsapp.data.remote.MyRetrofit
import com.zamahaka.wotsapp.data.remote.model.SearchUser
import com.zamahaka.wotsapp.data.remote.model.UserSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ura on 03.07.2017.
 */
class SearchUsersViewModel(application: Application) : AndroidViewModel(application) {
    val mUsers = MutableLiveData<List<SearchUser>>()

    fun setInput(userName: String) = MyRetrofit.wotApi.searchAccounts(userName).enqueue(object : Callback<UserSearchResponse> {
        override fun onFailure(call: Call<UserSearchResponse>, t: Throwable) {}

        override fun onResponse(call: Call<UserSearchResponse>, response: Response<UserSearchResponse>) {
            val data = response.body()?.data

            mUsers.value = data

            Log.d("myLog", "onResponse: data set")
        }
    })


    override fun onCleared() {
        Log.d("myLog", "onCleared: ")
    }
}