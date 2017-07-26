package com.zamahaka.wotsapp

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LifecycleRegistryOwner {

    private val viewModel by lazy { ViewModelProviders.of(this).get(SearchUsersViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("myLog", "onCreate: ")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.users.observe(this) {
            txtResponse.text = it.toString()
            Log.d("myLog", "users observed")
        }

        viewModel.error.observe(this) {
            txtResponse.text = it?.message.toString()
            Log.d("myLog", "error observed")
        }

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

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.database -> {
            viewModel.onOptionsItemSelected()
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
