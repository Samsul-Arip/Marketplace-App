package com.samsul.penjualanapp.ui.cart

import com.google.android.gms.common.api.Api
import com.samsul.penjualanapp.data.model.cart.ResponseCartList
import com.samsul.penjualanapp.data.model.cart.ResponseCartUpdate
import com.samsul.penjualanapp.data.model.cart.ResponseCheckout
import com.samsul.penjualanapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartPresenter(val view : CartContract.View) : CartContract.Presenter {

    init {
        view.iinitActivity()
        view.initListener()
    }

    override fun getCart(username: String) {
        view.onLoadingCart(true)
        ApiService.endPoint.getCart(username).enqueue(object : Callback<ResponseCartList> {
            override fun onResponse(
                call: Call<ResponseCartList>,
                response: Response<ResponseCartList>
            ) {
                view.onLoadingCart(false)
                if(response.isSuccessful) {
                    val responseCartList : ResponseCartList? = response.body()
                    view.onResultCart( responseCartList!! )
                }
            }

            override fun onFailure(call: Call<ResponseCartList>, t: Throwable) {
                view.onLoadingCart(false)
            }

        })
    }

    override fun deleteItemCart(kd_keranjang: Long) {
        ApiService.endPoint.deleteItemCart(kd_keranjang).enqueue(object : Callback<ResponseCartUpdate> {
            override fun onResponse(call: Call<ResponseCartUpdate>, response: Response<ResponseCartUpdate>) {
                view.onLoadingCart(false)
                if (response.isSuccessful) {
                    val responseCartUpdate : ResponseCartUpdate? = response.body()
                    view.onResultDelete( responseCartUpdate!! )
                }
            }

            override fun onFailure(call: Call<ResponseCartUpdate>, t: Throwable) {
                view.onLoadingCart(false)
            }

        })
    }

    override fun deleteCart(username: String) {
        ApiService.endPoint.deleteCart(username).enqueue(object : Callback<ResponseCartUpdate> {
            override fun onResponse(call: Call<ResponseCartUpdate>, response: Response<ResponseCartUpdate>) {
                view.onLoadingCart(false)
                if (response.isSuccessful) {
                    val responseCartUpdate : ResponseCartUpdate? = response.body()
                    view.onResultDelete( responseCartUpdate!! )
                    view.showMessage(responseCartUpdate.msg)
                }
            }

            override fun onFailure(call: Call<ResponseCartUpdate>, t: Throwable) {
                view.onLoadingCart(false)
            }

        })
    }

    override fun checkOut(username: String, kd_agent: Long) {
        view.onLoadingCart(true)
        ApiService.endPoint.checkOut(username, kd_agent).enqueue(object : Callback<ResponseCheckout> {
            override fun onResponse(call: Call<ResponseCheckout>, response: Response<ResponseCheckout>) {
                view.onLoadingCart(false)
                if(response.isSuccessful) {
                    val responseCheckout : ResponseCheckout? = response.body()
                    view.onResultCheckout( responseCheckout!! )
                    view.showMessage(responseCheckout.msg)
                }
            }

            override fun onFailure(call: Call<ResponseCheckout>, t: Throwable) {
                view.onLoadingCart(false)
            }

        })
    }

}