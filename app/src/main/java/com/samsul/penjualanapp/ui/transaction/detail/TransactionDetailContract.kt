package com.samsul.penjualanapp.ui.transaction.detail

import com.samsul.penjualanapp.data.model.transaction.ResponseTransactionList
import com.samsul.penjualanapp.data.model.transaction.detail.ResponseTransactionDetail

interface TransactionDetailContract {

    interface Presenter {
        fun getTransactionByInvoice(invoice : String)
    }

    interface View {
        fun initFragment()
        fun initListener(view : android.view.View)
        fun onLoading(loading:Boolean)
        fun onResult(responseTransactionDetail: ResponseTransactionDetail)
    }
}