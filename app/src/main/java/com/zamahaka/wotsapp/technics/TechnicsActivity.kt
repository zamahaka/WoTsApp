package com.zamahaka.wotsapp.technics

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.zamahaka.wotsapp.R
import kotlinx.android.synthetic.main.activity_technics.*

class TechnicsActivity : AppCompatActivity(), TechnicsContract.View {
    override val isActive get() = !isFinishing

    override var presenter: TechnicsContract.Presenter? = TechnicsPresenter().apply { view = this@TechnicsActivity }
        set(value) {
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_technics)

        recyclerVIew.layoutManager = LinearLayoutManager(this)
        recyclerVIew.adapter = TechnicsAdapter().apply { presenter = this@TechnicsActivity.presenter }

        swipeRefreshLayout.setOnRefreshListener { presenter?.refresh() }

        presenter?.start()
    }

    override fun onDestroy() {
        presenter?.view = null
        super.onDestroy()
    }

    override fun updateList() = recyclerVIew.adapter.notifyDataSetChanged()

    override fun setLoading(loading: Boolean) = with(swipeRefreshLayout) { isRefreshing = loading }
}
