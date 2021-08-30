package com.samsul.penjualanapp.ui.transaction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samsul.penjualanapp.R

class TransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initActivity()
    }

    fun initActivity() {
        supportFragmentManager.beginTransaction()
            .add(R.id.conta, TransactionFragment(), "transFrag")
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        if(supportFragmentManager.findFragmentByTag("transFrag") == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.conta, TransactionFragment(), "transFrag")
                    .commit()
        } else {
            finish()
        }
        return super.onSupportNavigateUp()
    }
}