package com.samsul.penjualanapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.samsul.penjualanapp.R
import com.samsul.penjualanapp.data.database.PrefsManager
import com.samsul.penjualanapp.data.model.login.ResponseLogin
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),LoginContract.View{

    lateinit var presenter : LoginPresenter
    lateinit var prefsManager: PrefsManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)
        prefsManager = PrefsManager(this)


    }


    override fun initActivity() {
        supportActionBar?.title = "Masuk"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }

    override fun initListener() {
        btnLogin.setOnClickListener {
            presenter.doLogin(edtUser.text.toString(), edtPassword.text.toString())
        }
    }

    override fun onLoading(loading : Boolean) {
        when(loading) {
            true -> {
                proggressBar.visibility = View.VISIBLE
                btnLogin.visibility = View.GONE
            }
            false -> {
                proggressBar.visibility = View.GONE
                btnLogin.visibility = View.VISIBLE
            }
        }
    }

    override fun onResult(responseLogin: ResponseLogin) {
        presenter.setPrefs(prefsManager,responseLogin.pegawai!!)
        finish()
    }

    override fun showMessage(message : String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


}