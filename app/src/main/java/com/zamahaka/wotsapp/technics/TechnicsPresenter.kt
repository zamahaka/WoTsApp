package com.zamahaka.wotsapp.technics

import android.util.Log
import com.zamahaka.wotsapp.data.remote.MyRetrofit
import com.zamahaka.wotsapp.data.remote.model.TankData
import com.zamahaka.wotsapp.enqueue
import com.zamahaka.wotsapp.performViewOperation
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by Ura on 26.08.2017.
 */
class TechnicsPresenter : TechnicsContract.Presenter {

    private val data: MutableList<TankData> = mutableListOf()

    override var view: TechnicsContract.View? = null
        get() = field
        set(value) {
            field = value
        }

    override val count get() = data.size

    override fun start() = loadData(true)

    override fun refresh() {
        data.clear()
        loadData(true)
        performViewOperation { updateList() }
    }

    override fun bind(view: TechnicsContract.ListView, position: Int): Unit = with(data[position]) {
        name?.let { view.setName(it) }
        images?.bigIcon?.let { view.loadImage(it) }
    }

    private fun loadData(showUi: Boolean) {
        if (showUi) performViewOperation { setLoading(true) }
        MyRetrofit.wotApi.technics().enqueue {
            onResponse {
                Log.d("myLog", "onResponse: ")
                val responseData = it.body()?.data

                responseData?.let { it.forEach { data.add(it.value) } }
                performViewOperation {
                    if (showUi) setLoading(false)
                    updateList()
                }
            }

            onFailure { Log.d("myLog", "onFailure: ") }
        }
    }
}