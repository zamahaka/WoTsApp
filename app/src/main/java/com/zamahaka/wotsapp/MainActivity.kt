package com.zamahaka.wotsapp

import android.arch.lifecycle.*
import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.zamahaka.wotsapp.data.local.SearchUsersDatabase
import com.zamahaka.wotsapp.data.local.entity.SearchUserEntity
import com.zamahaka.wotsapp.data.remote.model.SearchUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

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
            Log.d("myLog", "observed: ")

            async(UI) {
                bg {
                    it?.let {
                        searchUsersDb.usersDao().saveUsers(it.map { SearchUserEntity(it.accountId, it.nickName) })
                    }
                }.await()
            }
        }

        searchUsersDb.usersDao().getUsers().observe(this) { txtDatabase.text = it.toString() }

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
                val users = bg { searchUsersDb.usersDao().getUsers().value ?: listOf() }
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
