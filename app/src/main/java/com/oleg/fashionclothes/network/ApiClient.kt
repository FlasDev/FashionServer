package com.oleg.fashionclothes.network

import com.oleg.fashionclothes.network.module.Catalog
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by oleg on 13.03.2018.
 */
class ApiClient @Inject constructor(private val fashionService: FashionService): FashioClient {

    override fun getProduct(): Observable<Catalog> {
       return fashionService.getProduct()
    }

    companion object {
        const val BASE_URL: String = "https://fashionup.ua"
    }
}