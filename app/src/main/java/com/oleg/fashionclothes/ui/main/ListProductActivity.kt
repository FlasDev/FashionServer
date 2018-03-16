package com.oleg.fashionclothes.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import com.oleg.fashionclothes.R
import com.oleg.fashionclothes.ui.BaseActivity
import com.oleg.fashionclothes.utils.hide
import com.oleg.fashionclothes.utils.observeOnMainThread
import com.oleg.fashionclothes.utils.show
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindToLifecycle
import kotlinx.android.synthetic.main.activity_list_product.*
import javax.inject.Inject


class ListProductActivity : BaseActivity() {

    @Inject
    lateinit var vmFactory: ListProductFactory


    private lateinit var vm: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProviders.of(
                this, vmFactory).get(ListProductViewModel::class.java)
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
