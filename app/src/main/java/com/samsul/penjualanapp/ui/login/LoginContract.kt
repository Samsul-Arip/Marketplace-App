package com.samsul.penjualanapp.ui.login

import com.samsul.penjualanapp.data.database.PrefsManager
import com.samsul.penjualanapp.data.model.login.DataLogin
import com.samsul.penjualanapp.data.model.login.ResponseLogin

interface LoginContract {

    interface Presenter {
       fun doLogin(username : String, password : String)
       fun setPrefs(prefsManager: PrefsManager, dataLogin: DataLogin)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading : Boolean)
        fun onResult(responseLogin : ResponseLogin)
        fun showMessage(message : String)
    }
}