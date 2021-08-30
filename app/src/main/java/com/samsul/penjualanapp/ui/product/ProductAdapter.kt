package com.samsul.penjualanapp.ui.product

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samsul.penjualanapp.R
import com.samsul.penjualanapp.data.Constant
import com.samsul.penjualanapp.data.model.product.DataCategory
import com.samsul.penjualanapp.data.model.product.DataProduct
import com.samsul.penjualanapp.data.model.transaction.detail.DataDetail
import com.samsul.penjualanapp.utils.GlideHelper
import kotlinx.android.synthetic.main.adapter_category.view.*
import kotlinx.android.synthetic.main.adapter_product.view.*
import kotlinx.android.synthetic.main.adapter_transaction_detail.view.*
import kotlinx.android.synthetic.main.adapter_transaction_detail.view.imvImage
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class ProductAdapter(val mContext : Context, var product : ArrayList<DataProduct>) :
        RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val view  = view
        fun bind(product: DataProduct){
            view.txvName.text = product.nama_produk!!
            view.txvPrice.text = product.harga_rupiah!!
            view.txvStock.text = "Stok Tersedia (${product.stok})"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.adapter_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bind(product[position])
        GlideHelper.setImage(mContext, product[position].gambar_produk!!,holder.view.imvImage)
        holder.view.linProduct.setOnClickListener {
            Constant.PRODUCT_ID = product[position].kdProduk!!
            Constant.IS_CHANGE = true
            (mContext as Activity).finish()
        }
    }

    override fun getItemCount(): Int = product.size

    fun setData(newProduct : List<DataProduct>) {
        product.clear()
        product.addAll(newProduct)
        notifyDataSetChanged()
    }
}