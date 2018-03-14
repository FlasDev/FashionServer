package com.oleg.fashionclothes.di.model

import com.oleg.fashionclothes.network.NetworkModule
import dagger.Module

/**
 * Created by oleg on 13.03.2018.
 */
@Module(includes = [NetworkModule::class, ViewModelModule::class])
class AppModule {
}