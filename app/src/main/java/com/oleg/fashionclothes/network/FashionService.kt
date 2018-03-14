package com.oleg.fashionclothes.network

import com.oleg.fashionclothes.network.module.Catalog
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by oleg on 13.03.2018.
 */
interface FashionService {
    @GET("/api.php?act=pages&brand=1&page=1&format=1")
    fun getProduct(): Observable<Catalog>
}