package com.samsul.penjualanapp.ui.cart

import com.samsul.penjualanapp.data.model.cart.ResponseCheckout
import com.samsul.penjualanapp.data.model.cart.ResponseCartList
import com.samsul.penjualanapp.data.model.cart.ResponseCartUpdate

interface CartContract {

    interface Presenter {
        fun getCart(username : String)
        fun deleteItemCart(kd_keranjang : Long)
        fun deleteCart(username: String)

        fun checkOut(username: String, kd_agent :Long)
    }

    interface View {
        fun iinitActivity()
        fun initListener()
        fun onLoadingCart(loading : Boolean)
        fun onResultCart(responseCartList: ResponseCartList)
        fun showMessage(message : String)
        fun onResultDelete(responseCartUpdate: ResponseCartUpdate)
        fun showDialog()

        fun onLoadingCheckOut(loading: Boolean)
        fun onResultCheckout(responseCheckout: ResponseCheckout)
    }
}