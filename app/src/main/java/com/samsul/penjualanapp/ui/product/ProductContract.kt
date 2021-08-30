package com.samsul.penjualanapp.ui.product

import com.samsul.penjualanapp.data.model.product.ResponseCategoryList
import com.samsul.penjualanapp.data.model.product.ResponseProductList

interface ProductContract {

    interface Presenter {
        fun getCategory()
        fun getProduct(kd_kategori : Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading : Boolean)
        fun onResultCategory(responseCategoryList: ResponseCategoryList)
        fun onResultProduct(responseProductList: ResponseProductList)
    }
}