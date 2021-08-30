package com.samsul.penjualanapp.ui.home

class MainPresenter(val view : MainContract.View) {

    init {
        view.initListener()
    }
}