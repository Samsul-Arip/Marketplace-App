package com.samsul.penjualanapp.ui.home

interface MainContract {

    interface View {
        fun initListener()
        fun showMessage(msg : String)
    }
}