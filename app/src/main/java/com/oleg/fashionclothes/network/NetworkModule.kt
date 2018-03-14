package com.oleg.fashionclothes.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

/**
 * Created by oleg on 13.03.2018.
 */
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideFashionClient(): FashioClient{
        val retrofit = Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        val fashionService = retrofit.create(FashionService::class.java)

        return ApiClient(fashionService)
    }

}