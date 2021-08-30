package com.samsul.penjualanapp.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.samsul.penjualanapp.R
import com.samsul.penjualanapp.data.database.PrefsManager
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity(),UserContract.View {

    lateinit var prefsManager: PrefsManager
    lateinit var presenter: UserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        prefsManager = PrefsManager(this)
        presenter = UserPresenter(this)
        presenter.doLogin(prefsManager)
    }

    override fun initActivity() {
        supportActionBar!!.hide()

    }

    override fun initListener() {
        btnBack.setOnClickListener {
            finish()
        }
        tvLogout.setOnClickListener {
            presenter.doLogout(prefsManager)
        }
    }

    override fun onResultLogin(prefsManager: PrefsManager) {
        tvUser.text = prefsManager.prefsIsUsername
        tvNama.text = prefsManager.prefsIsNamaPegawai
        tvGender.text = prefsManager.prefsIsJk
        tvAlamat.text = prefsManager.prefsIsAlamat
    }

    override fun onResultLogout() {
        finish()
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}