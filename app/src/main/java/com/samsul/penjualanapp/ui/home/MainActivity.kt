package com.samsul.penjualanapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.samsul.penjualanapp.R
import com.samsul.penjualanapp.data.database.PrefsManager
import com.samsul.penjualanapp.ui.agent.AgentActivity
import com.samsul.penjualanapp.ui.login.LoginActivity
import com.samsul.penjualanapp.ui.transaction.TransactionActivity
import com.samsul.penjualanapp.ui.user.UserActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var prefsManager: PrefsManager
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefsManager = PrefsManager(this)
        presenter = MainPresenter(this)

    }

    override fun onStart() {
        super.onStart()
        when (prefsManager.prefsIsLogin) {
            true -> {
                crvUser.visibility = View.VISIBLE
                btnLogin.visibility = View.GONE
            }
            false -> {
                crvUser.visibility = View.GONE
                btnLogin.visibility = View.VISIBLE
            }
        }
    }

    override fun initListener() {

        crvTransaction.setOnClickListener {
            if(prefsManager.prefsIsLogin) {
                startActivity(Intent(this, TransactionActivity::class.java))
            } else {
                showMessage("Anda belum masuk")
            }
        }

        crvAgent.setOnClickListener {
            if(prefsManager.prefsIsLogin) {
                startActivity(Intent(this, AgentActivity::class.java))
            } else {
                showMessage("Anda belum masuk")
            }
        }



        crvUser.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }
        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun showMessage(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}