package com.oleg.fashionclothes.di.model

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.oleg.fashionclothes.di.factory.ViewModelFactory
import com.oleg.fashionclothes.di.scope.ViewModelKey
import com.oleg.fashionclothes.ui.main.ListProductViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by oleg on 13.03.2018.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListProductViewModel::class)
    abstract fun bindListProductViewModel(listProductViewModel: ListProductViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactoty(factory: ViewModelFactory): ViewModelProvider.Factory
}