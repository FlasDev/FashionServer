package com.oleg.fashionclothes.ui.main

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.oleg.fashionclothes.db.room.ProductDatabase
import com.oleg.fashionclothes.network.FashioClient
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by oleg on 14.03.2018.
 */
@Suppress("UNCHECKED_CAST")
@Singleton
class ListProductFactory @Inject constructor(application: Application,
                                             var fashionClient: FashioClient,
                                             var productDatabase: ProductDatabase
) : ViewModelProvider.AndroidViewModelFactory(application)
{
    private var application:Application = application


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListProductViewModel(application = application,fashionClient = fashionClient, productDatabase = productDatabase) as T
    }
}