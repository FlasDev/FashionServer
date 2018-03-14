package com.oleg.fashionclothes.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.oleg.fashionclothes.R
import com.oleg.fashionclothes.network.module.Offer
import com.oleg.fashionclothes.ui.adapter.holder.FashionItemHolder

/**
 * Created by oleg on 14.03.2018.
 */
class FashionItemAdapter(context:Context, list: List<Offer>): RecyclerView.Adapter<FashionItemHolder>() {
    private var listOffer: List<Offer>? = null
    private var mContext: Context? = null

    init {
        listOffer = list
        mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FashionItemHolder? {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.product_item,parent,false)
        return FashionItemHolder(v)
    }

    override fun getItemCount(): Int = listOffer?.size!!

    override fun onBindViewHolder(holder: FashionItemHolder?, position: Int) {
        var offer: Offer = listOffer?.get(position)!!
        holder?.bindItem(position = position, offer = offer)

    }
}