package com.zamahaka.wotsapp

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import android.util.Log
import com.zamahaka.wotsapp.data.local.SearchUsersDatabase
import com.zamahaka.wotsapp.data.local.entity.SearchUserEntity
import com.zamahaka.wotsapp.data.remote.MyRetrofit
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by Ura on 03.07.2017.
 */
class SearchUsersViewModel(application: Application) : AndroidViewModel(application) {
    val usersDao by lazy {
        Room.databaseBuilder(getApplication(), SearchUsersDatabase::class.java, "Wot.db").build().usersDao
    }

    val users = usersDao.getUsers()
    val error = MutableLiveData<Throwable>()

    fun setInput(userName: String) = if (userName.length >= 3) {
        MyRetrofit.wotApi.searchAccounts(userName).enqueue {
            onResponse {
                val data = it.body()?.data

                data?.let {
                    bg { usersDao.saveUsers(it.map { SearchUserEntity(it.accountId, it.nickName) }) }
                } ?: run { error.value = Throwable(it.body()?.error.toString()) }

                Log.d("myLog", "onResponse: data set")
            }

            onFailure {
                error.value = it

                Log.d("myLog", "onFailure: ")
            }
        }
    } else error.value = Throwable("Min username length is 3")

    fun onOptionsItemSelected() {
        async(UI) {
            val users = bg { usersDao.getUsers().value ?: listOf() }
            error.value = Throwable(users.await().toString())
        }
    }

    override fun onCleared() {
        Log.d("myLog", "onCleared: ")
    }
}