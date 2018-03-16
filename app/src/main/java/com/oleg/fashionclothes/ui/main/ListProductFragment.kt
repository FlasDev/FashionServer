package com.oleg.fashionclothes.ui.main


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import com.jakewharton.rxbinding2.view.RxMenuItem
import com.oleg.fashionclothes.R
import com.oleg.fashionclothes.ui.BaseFragment
import com.oleg.fashionclothes.utils.hide
import com.oleg.fashionclothes.utils.observeOnMainThread
import com.oleg.fashionclothes.utils.show
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindToLifecycle
import kotlinx.android.synthetic.main.fragment_list_product.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class ListProductFragment : BaseFragment() {

    @Inject
    lateinit var vmFactory: ListProductFactory

    private val vm: ListLoadVM by lazy{
        ViewModelProviders.of(
                this, vmFactory).get(ListProductViewModel::class.java)}


    override fun onCreateView(inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list_product, container, false)



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeUserInterface()
        setHasOptionsMenu(true)

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

    private fun initializeUserInterface() {
        initRecyclerView(recycler_view)
    }

    private fun initRecyclerView(recycler_view: RecyclerView?) {
        recycler_view?.layoutManager = vm.getLinearLayout()
        recycler_view?.adapter = vm.getFashionItemAdapter()
        vm.setRecyclerViewItem()

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.list_product_menu,menu)
        RxMenuItem.clicks(menu?.findItem(R.id.menu_download_data)!!)
                .compose(bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe({vm.getProduct()})

        RxMenuItem.clicks(menu.findItem(R.id.clear_db)!!)
                .compose(bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe({vm.deleteAllProduct()})

        RxMenuItem.clicks(menu.findItem(R.id.menu_save_server)!!)
                .compose(bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe({vm.saveToFireStore()})
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = false

    private fun showProgress() {
        progress.show()
    }

    private fun hideProgress() {
        progress.hide()
    }
}
