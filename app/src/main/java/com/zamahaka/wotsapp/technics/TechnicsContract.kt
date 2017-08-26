package com.zamahaka.wotsapp.technics

import com.zamahaka.wotsapp.data.remote.Url
import com.zamahaka.wotsapp.mvp.ActiveView
import com.zamahaka.wotsapp.mvp.BasePresenter

/**
 * Created by Ura on 26.08.2017.
 */
interface TechnicsContract {

    interface View : ActiveView {
        var presenter: Presenter?

        fun updateList()
        fun setLoading(loading: Boolean)
    }

    interface ListView {
        fun setName(name: String)
        fun loadImage(url: Url)
    }

    interface Presenter : BasePresenter<View>, ListPresenter {
        fun start()
        fun refresh()
    }

    interface ListPresenter {
        val count: Int
        fun bind(view: ListView, position: Int)
    }

}