package com.oleg.fashionclothes.ui.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.support.v7.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.oleg.fashionclothes.db.room.Product
import com.oleg.fashionclothes.db.room.ProductDatabase
import com.oleg.fashionclothes.network.FashioClient
import com.oleg.fashionclothes.network.module.Catalog
import com.oleg.fashionclothes.ui.adapter.FashionItemAdapter
import com.oleg.fashionclothes.utils.applySchedulers
import com.oleg.fashionclothes.utils.offerToProduct
import com.oleg.fashionclothes.utils.setListProduct
import com.oleg.fashionclothes.utils.withProgress
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject


/**
 * Created by oleg on 13.03.2018.
 */
interface Activity{

}

interface ListLoadVM{
    val progress: Observable<Boolean>
    fun getFashionItemAdapter(): FashionItemAdapter?
    fun getLinearLayout(): LinearLayoutManager?
    fun getProduct()
    fun deleteAllProduct()
    fun setRecyclerViewItem()
    fun saveToFireStore()
}


class ListProductViewModel(application: Application,
                           var fashionClient: FashioClient,
                           var productDatabase: ProductDatabase,
                           var firebaseFirestore: FirebaseFirestore
): AndroidViewModel(application), Activity, ListLoadVM{
    override fun saveToFireStore() {
        mCompositeDisposable?.add(getAllFlowable
                .withProgress(progress)
                .subscribe({t: List<Product> ->
                    Observable.fromIterable(t)
                            .map({t: Product ->
                                val products = HashMap<String, Any?>()
                                products["type"] = t.type
                                products["available"] = t.available
                                products["selling_type"] = t.selling_type
                                products["name"] = t.name
                                products["description"] = t.description
                                products["currencyId"] = t.currencyId
                                products["categoryId"] = t.categoryId
                                products["price"] = t.price
                                products["picture"] = t.picture
                                products["model"] = t.model
                                products["color"] = t.color
                                firebaseFirestore.collection("products")
                                        .add(products!!)
                            }).subscribe()
                })


        )
    }

    private var mCompositeDisposable: CompositeDisposable? = null
    private var getAllFlowable: Flowable<List<Product>> = productDatabase.productDao().getAll()

    init {
        mCompositeDisposable = CompositeDisposable()
    }


    override fun setRecyclerViewItem() {
        mCompositeDisposable?.add(getAllFlowable
                .withProgress(progress)
                .observeOn(AndroidSchedulers.mainThread())
                .filter({t -> t.isNotEmpty()})
                .compose(setListProduct(adapter!!))
                .subscribe())
    }


    override fun deleteAllProduct() {
        Observable.just("1")
                .withProgress(progress)
                .doOnNext({productDatabase.productDao().deleteAll()})
                .applySchedulers()
                .subscribe({adapter?.clearList()})
    }


    override val progress: PublishSubject<Boolean> = PublishSubject.create()


    private var adapter: FashionItemAdapter? = FashionItemAdapter(application.baseContext)
    override fun getFashionItemAdapter(): FashionItemAdapter? = adapter


    private var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(application.baseContext)
    override fun getLinearLayout(): LinearLayoutManager? = linearLayoutManager


     override fun getProduct(){

         mCompositeDisposable?.add(fashionClient.getProduct()
                 .withProgress(progress)
                 .map({t: Catalog -> t.shop?.offers?.offer })
                 .compose(offerToProduct())
                 .doOnNext({t: Product? ->  productDatabase.productDao().insert(t)})
                 .applySchedulers()
                 .subscribe())


    }

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable?.clear()
    }
}



















