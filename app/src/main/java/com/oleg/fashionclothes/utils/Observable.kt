package com.oleg.fashionclothes.utils

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

/**
 * Created by oleg on 14.03.2018.
 */
fun <T> Observable<T>.observeOnMainThread(): Observable<T>
        = observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.withProgress(
        progressSubject: PublishSubject<Boolean>) : Observable<T> {
    return compose{
        it.doOnSubscribe{
            progressSubject.onNext(true)
        }.doAfterTerminate{
            progressSubject.onNext(false)
        }
    }
}