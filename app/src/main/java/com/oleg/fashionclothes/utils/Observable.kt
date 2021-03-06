package com.oleg.fashionclothes.utils

import com.oleg.fashionclothes.db.room.Product
import com.oleg.fashionclothes.network.module.Offer
import com.oleg.fashionclothes.ui.adapter.FashionItemAdapter
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject


/**
 * Created by oleg on 14.03.2018.
 */
fun <T> Observable<T>.observeOnMainThread(): Observable<T>
        = observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.applySchedulers(): Observable<T>
    = subscribeOn(Schedulers.io()).observeOnMainThread()

fun <T> Flowable<T>.applySchedulers(): Flowable<T>
        = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.withProgress(
        progressSubject: PublishSubject<Boolean>): Observable<T> {

    return compose {
        it.doOnSubscribe {
            progressSubject.onNext(true)
        }.doAfterTerminate {
            progressSubject.onNext(false)
        }
    }
}

fun <T> Flowable<T>.withProgress(
        progressSubject: PublishSubject<Boolean>): Flowable<T> {

    return compose {
        it.doOnSubscribe {
            progressSubject.onNext(true)
        }.doAfterTerminate {
            progressSubject.onNext(false)
        }
    }
}


fun offerToProduct(): ObservableTransformer<List<Offer>?,Product>{

    return ObservableTransformer { upstream ->
        upstream.flatMapIterable({offer: List<Offer> -> offer.map {
            offer->
            Product(type = offer.type,
                    available = offer.available,
                    selling_type = offer.selling_type,
                    name = offer.name,
                    description = offer.description,
                    currencyId = offer.currencyId,
                    categoryId = offer.categoryId,
                    price = offer.price,
                    picture = offer.pictures,
                    model = offer.model,
                    color = offer.color
            )
        }

        })
    }
}

fun setListProduct(adapter: FashionItemAdapter): FlowableTransformer<List<Product>,List<Product>?>{
    return FlowableTransformer { upstream ->
        upstream
                .doOnNext({
            t: List<Product>? -> adapter.addListProduct(t!!)
        }

        )
    }
}














