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
class FashionItemAdapter(context:Context): RecyclerView.Adapter<FashionItemHolder>() {
    private  var listOffer: List<Offer>? = null
    private var mContext: Context? = null

    init {
        mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FashionItemHolder? {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.product_item,parent,false)

        return FashionItemHolder(v)
    }

    override fun getItemCount(): Int{
        var res: Int = 0
        if(listOffer != null){
            res = listOffer!!.size
        }
        return res
    }

    override fun onBindViewHolder(holder: FashionItemHolder?, position: Int) {
        var offer: Offer = listOffer!![position]
        holder?.bindItem(position = position, offer = offer)

    }


    fun addListOffer(list: List<Offer>) {
        listOffer = list
        notifyDataSetChanged()
    }


}