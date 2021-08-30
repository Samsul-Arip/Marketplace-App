package com.samsul.penjualanapp.ui.login

import com.samsul.penjualanapp.data.database.PrefsManager
import com.samsul.penjualanapp.data.model.login.DataLogin
import com.samsul.penjualanapp.data.model.login.ResponseLogin
import com.samsul.penjualanapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter (val view : LoginContract.View) : LoginContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun doLogin(username: String, password: String) {
        view.onLoading(true)
        ApiService.endPoint.loginUser(username, password)
            .enqueue(object : Callback<ResponseLogin> {

                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    view.onLoading(false)
                    if(response.isSuccessful){
                        val responLogin : ResponseLogin? = response.body()
                        view.showMessage(responLogin!!.msg)

                        if(responLogin!!.status) {
                            view.onResult(responLogin)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    view.onLoading(false)

                }

            })
    }

    override fun setPrefs(prefsManager: PrefsManager, dataLogin: DataLogin) {
        prefsManager.prefsIsLogin = true
        prefsManager.prefsIsUsername = dataLogin.username!!
        prefsManager.prefsIsPassword = dataLogin.password!!
        prefsManager.prefsIsNamaPegawai = dataLogin.nama_pegawai!!
        prefsManager.prefsIsJk = dataLogin.jk!!
        prefsManager.prefsIsAlamat = dataLogin.alamat!!
        prefsManager.prefsIsAktf = dataLogin.is_aktif!!


    }

}