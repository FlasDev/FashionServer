package com.oleg.fashionclothes.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.oleg.fashionclothes.R
import com.oleg.fashionclothes.db.room.Product
import com.oleg.fashionclothes.ui.adapter.holder.FashionItemHolder

/**
 * Created by oleg on 14.03.2018.
 */
class FashionItemAdapter(context:Context): RecyclerView.Adapter<FashionItemHolder>() {
    private  var listProduct: List<Product>? = null
    private var mContext: Context? = null

    init {
        Log.d("myLogs","дися")
        mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FashionItemHolder? {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.product_item,parent,false)

        return FashionItemHolder(v)
    }

    override fun getItemCount(): Int{
        var res: Int = 0
        if(listProduct != null){
            res = listProduct!!.size
        }
        return res
    }

    override fun onBindViewHolder(holder: FashionItemHolder?, position: Int) {
        var offer: Product = listProduct!![position]
        holder?.bindItem(offer = offer)

    }


    fun addListProduct(list: List<Product>) {
        listProduct = list
        notifyDataSetChanged()
    }

    fun clearList(){
        listProduct = null
        notifyDataSetChanged()
    }


}