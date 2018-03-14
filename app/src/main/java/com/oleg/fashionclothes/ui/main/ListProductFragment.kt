package com.oleg.fashionclothes.ui.main


import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oleg.fashionclothes.R
import com.oleg.fashionclothes.network.FashioClient
import com.oleg.fashionclothes.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_list_product.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class ListProductFragment : BaseFragment() {

    @Inject
    lateinit var fashioClient: FashioClient

   private lateinit var vm: ListLoadVM

    override fun onCreateView(inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list_product, container, false)



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = ViewModelProviders.of(this,ListProductFactory(application = activity?.application!!,fashionClient = fashioClient)).get(ListProductViewModel::class.java)
        initializeUserInterface()
    }

    private fun initializeUserInterface() {
        initRecyclerView(recycler_view)
    }

    @SuppressLint("CheckResult")
    private fun initRecyclerView(recycler_view: RecyclerView?) {

        recycler_view?.layoutManager = vm.getLinearLayout()
        recycler_view?.adapter = vm.getFashionItemAdapter()

    }

}
