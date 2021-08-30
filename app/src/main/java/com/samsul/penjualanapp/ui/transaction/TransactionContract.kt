package com.samsul.penjualanapp.ui.transaction

import com.samsul.penjualanapp.data.model.transaction.ResponseTransactionList

interface TransactionContract {

    interface Presenter {
        fun getTransactionByUsername(username : String)
    }

    interface View {
        fun initFragment()
        fun initListener(view : android.view.View)
        fun onLoading(loading:Boolean)
        fun onResult(responseTransactionList: ResponseTransactionList)
        fun onClickTransaction(invoice:String)
    }
}