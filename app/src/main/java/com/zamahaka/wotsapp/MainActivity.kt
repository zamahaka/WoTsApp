package com.zamahaka.wotsapp

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.zamahaka.wotsapp.data.local.SearchUsersDatabase
import com.zamahaka.wotsapp.data.local.entity.SearchUserEntity
import com.zamahaka.wotsapp.data.remote.MyRetrofit
import com.zamahaka.wotsapp.data.remote.model.SearchUser
import com.zamahaka.wotsapp.data.remote.model.UserSearchResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : LifecycleActivity() {

    val searchUsersDb: SearchUsersDatabase by lazy {
        Room.databaseBuilder(
                applicationContext, SearchUsersDatabase::class.java, "Wot.db")
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("myLog", "onCreate: ")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(SearchUsersViewModel::class.java)
        viewModel.mUsers.observe(this,
                Observer<List<SearchUser>> {
                    txtResponse.text = it.toString()
                    Log.d("myLog", "observed: ")

                    it?.let { searchUsersDb.usersDao().saveUsers(it.map { SearchUserEntity(it.accountId, it.nickName) }) }
                })

        edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) = viewModel.setInput(s.toString())

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onStart() {
        super.onStart()
        Log.d("myLog", "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("myLog", "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("myLog", "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d("myLog", "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("myLog", "onDestroy: ")
    }
}
