package com.oleg.fashionclothes.ui.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.oleg.fashionclothes.network.FashioClient
import com.oleg.fashionclothes.network.module.Catalog
import com.oleg.fashionclothes.network.module.Offer
import com.oleg.fashionclothes.ui.adapter.FashionItemAdapter
import com.oleg.fashionclothes.utils.withProgress
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

/**
 * Created by oleg on 13.03.2018.
 */
interface Activity{
    val progress: Observable<Boolean>
}

interface ListLoadVM{
    fun getFashionItemAdapter(): FashionItemAdapter?
    fun getLinearLayout(): LinearLayoutManager?
}

class ListProductViewModel(application: Application,var fashionClient: FashioClient): AndroidViewModel(application), Activity, ListLoadVM {


    override val progress: PublishSubject<Boolean> = PublishSubject.create()
    init {
        getProduct()
    }

    private var adapter: FashionItemAdapter? = FashionItemAdapter(application.baseContext)
    override fun getFashionItemAdapter(): FashionItemAdapter? = adapter


    private var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(application.baseContext)
    override fun getLinearLayout(): LinearLayoutManager? = linearLayoutManager


     fun getProduct(){
        fashionClient.getProduct()
                .withProgress(progress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext({Log.d("myLogs","$adapter")})
                .map {t: Catalog -> t.shop?.offers?.offer }
                .subscribe({list: List<Offer>? -> adapter?.addListOffer(list!!)})
    }
}