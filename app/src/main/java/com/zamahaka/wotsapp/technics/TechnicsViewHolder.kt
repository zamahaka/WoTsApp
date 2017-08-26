package com.zamahaka.wotsapp.technics

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.zamahaka.wotsapp.R
import com.zamahaka.wotsapp.data.remote.Url
import com.zamahaka.wotsapp.load
import kotlinx.android.synthetic.main.item_technic.view.*

/**
 * Created by Ura on 26.08.2017.
 */
class TechnicsViewHolder(view: View) : RecyclerView.ViewHolder(view), TechnicsContract.ListView {

    private val imageView: ImageView = view.image
    private val nameView: TextView = view.name

    override fun setName(name: String) {
        nameView.text = name
    }

    override fun loadImage(url: Url) = imageView.load(url)

    companion object {
        @LayoutRes const val LAYOUT_ID = R.layout.item_technic
    }

}