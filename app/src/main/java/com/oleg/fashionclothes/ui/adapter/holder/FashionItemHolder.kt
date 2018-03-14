package com.oleg.fashionclothes.ui.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.oleg.fashionclothes.network.module.Offer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_item.view.*

/**
 * Created by oleg on 14.03.2018.
 */
class FashionItemHolder(var v: View): RecyclerView.ViewHolder(v) {

    init {

    }
    fun bindItem(position: Int, offer: Offer){
        Picasso.get()
                .load(offer.pictures?.get(0))
                .into(v.ImageProduct)
        v.NameProduct.text = offer.name
        v.PriceProduct.text = "Цена: ${offer.price}"
    }
}