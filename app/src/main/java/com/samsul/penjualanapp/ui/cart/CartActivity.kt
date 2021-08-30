package com.samsul.penjualanapp.ui.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.samsul.penjualanapp.R
import com.samsul.penjualanapp.data.Constant
import com.samsul.penjualanapp.data.database.PrefsManager
import com.samsul.penjualanapp.data.model.cart.DataCart
import com.samsul.penjualanapp.data.model.cart.ResponseCartList
import com.samsul.penjualanapp.data.model.cart.ResponseCartUpdate
import com.samsul.penjualanapp.data.model.cart.ResponseCheckout
import com.samsul.penjualanapp.ui.agent.search.AgentSearchActivity
import com.samsul.penjualanapp.ui.cart.add.CartAddActivity
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity(), CartContract.View {

    lateinit var prefsManager: PrefsManager
    lateinit var cartAdapter: CartAdapter
    lateinit var cartPresenter: CartPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        prefsManager = PrefsManager(this)
        cartPresenter = CartPresenter(this)
        cartPresenter.getCart(prefsManager.prefsIsUsername)

    }

    override fun onStart() {
        super.onStart()
        if(Constant.IS_CHANGE) {
            Constant.IS_CHANGE = false
            cartPresenter.getCart(prefsManager.prefsIsUsername)
            edAgent.setText(Constant.AGENT_NAME)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Constant.AGENT_NAME = ""

    }

    override fun iinitActivity() {
        supportActionBar!!.title = "Keranjang"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        
        cartAdapter = CartAdapter(this, arrayListOf()){
            dataCart, position ->
            cartPresenter.deleteItemCart(dataCart.kd_keranjang!!)
        }
    }

    override fun initListener() {
        tvReset.visibility = View.GONE
        edAgent.visibility = View.GONE

        rvCart.apply { layoutManager = LinearLayoutManager(context)
            adapter = cartAdapter
        }
        swipeCart.setOnRefreshListener {
            cartPresenter.getCart(prefsManager.prefsIsUsername)
        }
        btnAdd.setOnClickListener {
            startActivity(Intent(this, CartAddActivity::class.java))
        }
        tvReset.setOnClickListener {
            showDialog()
        }
        edAgent.setOnClickListener {
            startActivity(Intent(this, AgentSearchActivity::class.java))
        }
        btnCheckOut.setOnClickListener {
            if(cartAdapter.cart.isNullOrEmpty()) {
                showMessage("Keranjang Kosong")
            } else {
                if(edAgent.text.isNullOrEmpty()){
                    edAgent.error = "Tidak Boleh Kosong"
                } else {
                    cartPresenter.checkOut(prefsManager.prefsIsUsername, Constant.AGENT_ID)
                }
            }
        }
    }

    override fun onLoadingCart(loading: Boolean) {
        when(loading) {
            true -> swipeCart.isRefreshing = true
            false -> swipeCart.isRefreshing = false
        }
    }

    override fun onResultCart(responseCartList: ResponseCartList) {
        val dataCart : List<DataCart> = responseCartList.dataCart
        if(dataCart.isNullOrEmpty()) {
            tvReset.visibility = View.GONE
            edAgent.visibility = View.GONE
        } else {
            cartAdapter.setData(dataCart)
            tvReset.visibility = View.VISIBLE
            edAgent.visibility = View.VISIBLE
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onResultDelete(responseCartUpdate: ResponseCartUpdate) {
        cartPresenter.getCart(prefsManager.prefsIsUsername)
        cartAdapter.removeAll()
    }

    override fun showDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Konfirmasi")
        dialog.setMessage("Hapus semua item dalam keranjang")
        dialog.setPositiveButton("Hapus") { dialog, which ->
            cartPresenter.deleteCart(prefsManager.prefsIsUsername)
            dialog.dismiss()
        }

        dialog.setNegativeButton("Batal") { dialog, which ->
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onLoadingCheckOut(loading: Boolean) {
        when(loading) {
            true -> {
                btnCheckOut.isEnabled = false
                btnCheckOut.setText("Memuat.....")
            }
            false -> {
                btnCheckOut.isEnabled = true
                btnCheckOut.setText("Checkout")
            }
        }
    }

    override fun onResultCheckout(responseCheckout: ResponseCheckout) {
        cartPresenter.getCart(prefsManager.prefsIsUsername)
        cartAdapter.removeAll()
    }
}