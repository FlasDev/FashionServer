package com.oleg.fashionclothes.ui.main

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.oleg.fashionclothes.network.FashioClient

/**
 * Created by oleg on 14.03.2018.
 */
class ListProductFactory(application: Application,var fashionClient: FashioClient) : ViewModelProvider.AndroidViewModelFactory(application) {
    private var application:Application = application


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListProductViewModel(application = application!!,fashionClient = fashionClient!!) as T
    }
}