package com.oleg.fashionclothes.di.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Created by oleg on 13.03.2018.
 */
@Singleton
class ViewModelFactory @Inject constructor(
        private val viewModelProviders: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val provider = viewModelProviders[modelClass]
                ?: viewModelProviders.entries.first{modelClass.isAssignableFrom(it.key)}.value

        return provider.get() as T
    }
}