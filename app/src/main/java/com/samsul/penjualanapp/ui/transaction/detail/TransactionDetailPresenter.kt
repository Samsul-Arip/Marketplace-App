package com.samsul.penjualanapp.ui.transaction.detail

import com.samsul.penjualanapp.data.model.transaction.detail.ResponseTransactionDetail
import com.samsul.penjualanapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionDetailPresenter(val view : TransactionDetailContract.View) : TransactionDetailContract.Presenter {

    init {
        view.initFragment()
    }

    override fun getTransactionByInvoice(invoice: String) {
        view.onLoading(true)
        ApiService.endPoint.getTransactionByInvoice(invoice).enqueue(object : Callback<ResponseTransactionDetail> {
            override fun onResponse(call: Call<ResponseTransactionDetail>, response: Response<ResponseTransactionDetail>) {
                view.onLoading(false)
                if(response.isSuccessful) {
                    val responseTransactionDetail : ResponseTransactionDetail? = response.body()
                    view.onResult(responseTransactionDetail!!)
                }
            }

            override fun onFailure(call: Call<ResponseTransactionDetail>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }
}