package com.oleg.fashionclothes.ui.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.oleg.fashionclothes.network.module.Offer
import kotlinx.android.synthetic.main.product_item.view.*

/**
 * Created by oleg on 14.03.2018.
 */
class FashionItemHolder(var v: View): RecyclerView.ViewHolder(v) {

    init {

    }
    fun bindItem(position: Int, offer: Offer){
        v.textName.text = offer.name
    }
}