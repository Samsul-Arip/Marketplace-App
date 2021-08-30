package com.samsul.penjualanapp.ui.cart.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.samsul.penjualanapp.R
import com.samsul.penjualanapp.data.Constant
import com.samsul.penjualanapp.data.database.PrefsManager
import com.samsul.penjualanapp.data.model.cart.ResponseCartUpdate
import com.samsul.penjualanapp.ui.product.ProductActivity
import kotlinx.android.synthetic.main.activity_cart_add.*
import kotlinx.android.synthetic.main.activity_login.*

class CartAddActivity : AppCompatActivity(), CartAddContract.View {

    lateinit var prefsManager: PrefsManager
    lateinit var cartPresenter : CartAddPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_add)
        prefsManager = PrefsManager(this)
        cartPresenter = CartAddPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        if(Constant.IS_CHANGE) {
            Constant.IS_CHANGE = false
            edtProduct.setText(Constant.PRODUCT_NAME)
            txvQty.visibility = View.VISIBLE
            npQuantity.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Constant.PRODUCT_ID = 0
        Constant.PRODUCT_NAME = ""
        Constant.PRODUCT_QT = 0
    }

    override fun initActivity() {
        supportActionBar!!.title = "Tambah Produk"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        txvQty.visibility = View.GONE
        npQuantity.visibility = View.GONE


    }

    override fun initListener() {
        edtProduct.setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }

        npQuantity.minValue = 1
        npQuantity.maxValue = 25
        npQuantity.wrapSelectorWheel = true
        npQuantity.setOnValueChangedListener { picker, oldVal, newVal ->
            Constant.PRODUCT_QT = newVal.toLong()
        }
        btnSubmit.setOnClickListener {
            if (Constant.PRODUCT_ID > 0) {
                Constant.PRODUCT_QT = if (Constant.PRODUCT_QT > 0) Constant.PRODUCT_QT else 1
                cartPresenter.addCart(
                        prefsManager.prefsIsUsername, Constant.PRODUCT_ID, Constant.PRODUCT_QT
                )
            } else {
                edtProduct.error = "Tidak Boleh Kosong"
            }
        }
    }

    override fun onLoading(loading: Boolean) {
        when(loading) {
            true -> {
                progress.visibility = View.VISIBLE
                btnSubmit.visibility = View.GONE
            }
            false -> {
                progress.visibility = View.GONE
                btnSubmit.visibility = View.VISIBLE
            }
        }
    }

    override fun onResult(responseCartUpdate: ResponseCartUpdate) {
        if(responseCartUpdate.status) {
            Constant.IS_CHANGE = true
            finish()
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return super.onNavigateUp()
    }
}