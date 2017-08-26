package com.zamahaka.wotsapp.technics

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Ura on 26.08.2017.
 */
class TechnicsAdapter : RecyclerView.Adapter<TechnicsViewHolder>() {

    var presenter: TechnicsContract.ListPresenter? = null

    override fun getItemCount() = presenter?.count ?: 0

    override fun onBindViewHolder(holder: TechnicsViewHolder, position: Int) =
            presenter?.bind(holder, position) ?: Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TechnicsViewHolder(
            LayoutInflater.from(parent.context)
                    .inflate(TechnicsViewHolder.LAYOUT_ID, parent, false)
    )
}