package com.oleg.fashionclothes.di.component

import android.app.Application
import com.oleg.fashionclothes.App
import com.oleg.fashionclothes.di.model.ActivityBuilder
import com.oleg.fashionclothes.di.model.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by oleg on 13.03.2018.
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class, AndroidSupportInjectionModule::class])
interface AppComponent: AndroidInjector<App> {

    @Component.Builder
    interface Builder{
        fun build(): AppComponent
        @BindsInstance
        fun application(application: Application): Builder
    }

}