package com.oleg.fashionclothes.ui.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.oleg.fashionclothes.db.room.Product
import com.oleg.fashionclothes.db.room.ProductDatabase
import com.oleg.fashionclothes.network.FashioClient
import com.oleg.fashionclothes.network.module.Catalog
import com.oleg.fashionclothes.ui.adapter.FashionItemAdapter
import com.oleg.fashionclothes.utils.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit



/**
 * Created by oleg on 13.03.2018.
 */
interface Activity{
    val progress: Observable<Boolean>
}

interface ListLoadVM{
    fun getFashionItemAdapter(): FashionItemAdapter?
    fun getLinearLayout(): LinearLayoutManager?
    fun getProduct()
    fun deleteAllProduct()
    fun setRecyclerViewItem()
}

class ListProductViewModel(application: Application,
                           var fashionClient: FashioClient,
                           var productDatabase: ProductDatabase
): AndroidViewModel(application), Activity, ListLoadVM {
    private var mCompositeDisposable: CompositeDisposable? = null
    private var disposableRecycler: Disposable? = null
    private var disposableGetProduct: Disposable? = null
    private var disposableDeleteProduct: Disposable? = null

    init {
        mCompositeDisposable = CompositeDisposable()
    }


    override fun setRecyclerViewItem() {
        disposableRecycler = productDatabase.productDao().getAll()
                .doOnNext({Log.d("myLogs","зашел чекать бд")})
                .filter({t -> t.isNotEmpty()})
                .doOnNext({t->Log.d("myLogs","кол-во продуктов ${t.size}")})
                .compose(setListProduct(adapter!!))
                .subscribe()
    }


    override fun deleteAllProduct() {
        val productDao = productDatabase.productDao()
       disposableDeleteProduct = productDao.getAll()
               .withProgress(progress)
               .doOnNext({t: List<Product>? -> productDao.deleteAll(t!!)})
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe({adapter?.clearList()})

        mCompositeDisposable?.addAll(disposableRecycler,disposableDeleteProduct)
        mCompositeDisposable?.clear()
    }


    override val progress: PublishSubject<Boolean> = PublishSubject.create()


    private var adapter: FashionItemAdapter? = FashionItemAdapter(application.baseContext)
    override fun getFashionItemAdapter(): FashionItemAdapter? = adapter


    private var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(application.baseContext)
    override fun getLinearLayout(): LinearLayoutManager? = linearLayoutManager


     override fun getProduct(){

         disposableGetProduct = fashionClient.getProduct()
                 .withProgress(progress)
                 .applySchedulers()
                 .doOnNext({Log.d("myLogs","начал выгружать данные из сети")})
                 .map({t: Catalog -> t.shop?.offers?.offer })
                 .subscribe({list -> Observable.fromIterable(list)
                         .subscribeOn(Schedulers.io())
                         .compose(offerToProduct())
                         .compose(loadToRoom(productDatabase = productDatabase))
                         .debounce(1,TimeUnit.SECONDS)
                         .subscribe({
                             productDatabase.productDao().getAll()
                                     .compose(setListProduct(adapter!!))
                                     .subscribe()
                         })
                 })
         mCompositeDisposable?.addAll(disposableGetProduct,disposableRecycler)
         mCompositeDisposable?.clear()

    }


}














