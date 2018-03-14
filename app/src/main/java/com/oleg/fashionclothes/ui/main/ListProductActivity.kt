package com.oleg.fashionclothes.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import com.oleg.fashionclothes.R
import com.oleg.fashionclothes.ui.BaseActivity
import javax.inject.Inject

class ListProductActivity : BaseActivity() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private lateinit var vm: Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_product)
        showFragment()

    }

    private fun showFragment() {
        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ListProductFragment())
                    .commit()
        }
    }
}
