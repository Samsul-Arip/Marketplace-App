package com.samsul.penjualanapp.ui.transaction

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.samsul.penjualanapp.R
import com.samsul.penjualanapp.data.Constant
import com.samsul.penjualanapp.data.database.PrefsManager
import com.samsul.penjualanapp.data.model.transaction.DataTransaction
import com.samsul.penjualanapp.data.model.transaction.ResponseTransactionList
import com.samsul.penjualanapp.ui.cart.CartActivity
import com.samsul.penjualanapp.ui.transaction.detail.TransactionDetailFragment
import kotlinx.android.synthetic.main.fragment_transaction.*

class TransactionFragment : Fragment(), TransactionContract.View {

    lateinit var prefsManager: PrefsManager
    lateinit var transactionAdapter : TransactionAdapter
    lateinit var presenter: TransactionPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_transaction, container, false)

        prefsManager = PrefsManager(requireContext())
        presenter = TransactionPresenter(this)
        initListener(view)

        return view
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.title = "Transaksi"
        presenter.getTransactionByUsername( prefsManager.prefsIsUsername)
    }

    override fun initFragment() {
        transactionAdapter = TransactionAdapter(requireContext(), arrayListOf()){
            dataTransaction, position ->
            onClickTransaction(dataTransaction.no_faktur!!)
        }
    }

    override fun initListener(view: View) {
        val rvTransaction = view.findViewById<RecyclerView>(R.id.rvTransaction)
        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        rvTransaction!!.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionAdapter
        }
        swipe.setOnRefreshListener {
            presenter.getTransactionByUsername(prefsManager.prefsIsUsername)
        }
        fab.setOnClickListener {
            requireContext().startActivity(Intent(context, CartActivity::class.java))
        }
    }

    override fun onLoading(loading: Boolean) {
        when(loading) {
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResult(responseTransactionList: ResponseTransactionList) {
        val dataTransaction : List<DataTransaction> = responseTransactionList.dataTransaction
        transactionAdapter.setData(dataTransaction)
    }

    override fun onClickTransaction(invoice: String) {
        Constant.INVOICE = invoice
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.conta, TransactionDetailFragment(), "transDetail")
            .commit()
    }

}