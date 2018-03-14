package com.oleg.fashionclothes.ui.main

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.oleg.fashionclothes.network.FashioClient
import com.oleg.fashionclothes.network.module.Catalog
import com.oleg.fashionclothes.network.module.Offer
import com.oleg.fashionclothes.ui.adapter.FashionItemAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by oleg on 13.03.2018.
 */
interface Activity{

}

interface ListLoadVM{

    fun setLayoutManager(layoutManager: LinearLayoutManager)
    // fun getAdapter(context: Context): FashionItemAdapter
}

class ListProductViewModel @Inject constructor(private val fashionClient: FashioClient): ViewModel(), Activity, ListLoadVM {
  //  override fun getAdapter(context: Context): FashionItemAdapter = FashionItemAdapter(context = context)

    private var linearLayoutManager: LinearLayoutManager? = null

    override fun setLayoutManager(layoutManager: LinearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager
    }

     fun getProduct(context: Context){
        Log.d("myLogs","тут")
        fashionClient.getProduct()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
                .map({t: Catalog -> t.shop?.offers?.offer })
               .subscribe({
                   t: List<Offer>? -> FashionItemAdapter(context = context, list = t!!)
               })
    }
}