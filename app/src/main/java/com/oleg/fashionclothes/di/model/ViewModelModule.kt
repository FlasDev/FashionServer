package com.oleg.fashionclothes.di.model

import android.app.Application
import com.oleg.fashionclothes.db.room.ProductDatabase
import com.oleg.fashionclothes.network.FashioClient
import com.oleg.fashionclothes.network.NetworkModule
import com.oleg.fashionclothes.ui.main.ListProductFactory
import dagger.Module
import dagger.Provides

/**
 * Created by oleg on 13.03.2018.
 */
@Module(includes = [NetworkModule::class])
class ViewModelModule {

    @Provides
    fun provideListProductFactory(application: Application,
                                  fashionClient: FashioClient,
                                  productDatabase: ProductDatabase
    ): ListProductFactory = ListProductFactory(application = application, fashionClient = fashionClient,productDatabase = productDatabase)


}