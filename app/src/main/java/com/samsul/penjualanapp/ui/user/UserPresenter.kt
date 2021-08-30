package com.samsul.penjualanapp.ui.user

import com.samsul.penjualanapp.data.database.PrefsManager

class UserPresenter (val view : UserContract.View) : UserContract.Presenter {


    init {
        view.initActivity()
        view.initListener()
    }

    override fun doLogin(prefsManager: PrefsManager) {
        if(prefsManager.prefsIsLogin) {
            view.onResultLogin(prefsManager)
        }
    }

    override fun doLogout(prefsManager: PrefsManager) {
        prefsManager.logOut()
        view.showMessage("Berhasil Keluar")
        view.onResultLogout()
    }
}