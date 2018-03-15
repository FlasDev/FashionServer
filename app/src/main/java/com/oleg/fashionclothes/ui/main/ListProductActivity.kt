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

    private val vm: Activity by lazy{
        ViewModelProviders.of(
                this, vmFactory).get(ListProductViewModel::class.java)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list_product)

        showFragment()

        vm.progress.bindToLifecycle(this).observeOnMainThread().subscribe({
            Log.d("myLogs","vm.progress.bindToLifecycle(this).observeOnMainThr")
            if (it){
                Log.d("myLogs","showProgress")
                showProgress()
            }else{
                Log.d("myLogs","hideProgress")
                hideProgress()
            }
        })


    }

    private fun showFragment() {
        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ListProductFragment())
                    .commit()
        }
    }

    private fun showProgress() {
        progress.show()
    }

    private fun hideProgress() {
        progress.hide()
    }
}
