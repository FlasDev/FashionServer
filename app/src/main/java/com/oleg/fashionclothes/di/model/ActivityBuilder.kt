package com.oleg.fashionclothes.di.model

import com.oleg.fashionclothes.ui.main.ListProductActivity
import com.oleg.fashionclothes.ui.main.ListProductFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by oleg on 13.03.2018.
 */
@Module
abstract class ActivityBuilder{

    @ContributesAndroidInjector
    abstract fun bindListProductActivity(): ListProductActivity

    @ContributesAndroidInjector
    abstract fun bindListProductFragment(): ListProductFragment

}