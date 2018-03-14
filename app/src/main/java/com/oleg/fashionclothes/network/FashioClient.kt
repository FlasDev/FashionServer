package com.oleg.fashionclothes.network

import com.oleg.fashionclothes.network.module.Catalog
import io.reactivex.Observable

/**
 * Created by oleg on 13.03.2018.
 */
interface FashioClient {
    fun getProduct(): Observable<Catalog>
}