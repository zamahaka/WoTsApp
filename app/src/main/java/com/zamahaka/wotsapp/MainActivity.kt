package com.zamahaka.wotsapp

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.zamahaka.wotsapp.data.local.SearchUsersDatabase
import com.zamahaka.wotsapp.data.remote.MyRetrofit
import com.zamahaka.wotsapp.data.remote.model.TankopediaInfoResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), LifecycleRegistryOwner {

    val searchUsersDb: SearchUsersDatabase by lazy {
        Room.databaseBuilder(applicationContext, SearchUsersDatabase::class.java, "Wot.db")
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("myLog", "onCreate: ")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(SearchUsersViewModel::class.java)
        viewModel.mUsers.observe(this) {
            txtResponse.text = it.toString()
            Log.d("myLog", "users observed")
        }

        viewModel.mError.observe(this) {
            txtResponse.text = it?.message.toString()
            Log.d("myLog", "error observed")
        }

        MyRetrofit.wotApi.tankopediaInfo().enqueue(response = {
            call: Call<TankopediaInfoResponse>, response: Response<TankopediaInfoResponse> ->
            txtResponse.text = response.body()?.data.toString()
        }, failure = {
            call: Call<TankopediaInfoResponse>, throwable: Throwable? ->
            txtResponse.text = throwable?.message
        })

        searchUsersDb.usersDao.getUsers().observe(this) { txtDatabase.text = it.toString() }

        edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) = viewModel.setInput(s.toString())

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.database -> {
            async(UI) {
                val users = bg { searchUsersDb.usersDao.getUsers().value ?: listOf() }
                txtResponse.text = users.await().toString()
            }
            true
        }
        else -> super.onOptionsItemSelected(item)
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

    private val mRegistry = LifecycleRegistry(this)
    override fun getLifecycle() = mRegistry
}
