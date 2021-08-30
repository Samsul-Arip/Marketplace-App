package com.samsul.penjualanapp.ui.transaction.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samsul.penjualanapp.R
import com.samsul.penjualanapp.data.model.transaction.detail.DataDetail
import com.samsul.penjualanapp.utils.GlideHelper
import kotlinx.android.synthetic.main.adapter_transaction_detail.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class TransactionDetailAdapter(val mContext : Context, var detail : ArrayList<DataDetail>) :
        RecyclerView.Adapter<TransactionDetailAdapter.ViewHolder>() {


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val view  = view
        fun bind(detail: DataDetail){
            view.tvCategory.text = detail.kategori
            view.tvNameProduct.text = detail.nama_produk
            view.tvPrice.text = "${detail.harga_rupiah} x ${detail.jumlah}"

            val total : Double = detail!!.jumlah!!.toDouble() * detail!!.harga!!.toDouble()
            val hargaRupiah : String = NumberFormat.getNumberInstance(Locale.GERMANY).format(total)

            view.tvTotal.text = "Rp $hargaRupiah"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionDetailAdapter.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.adapter_transaction_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bind(detail[position])
        GlideHelper.setImage(mContext, detail[position].gambar_produk!!,holder.view.imvImage)
    }

    override fun getItemCount(): Int = detail.size

    fun setData(newDetail : List<DataDetail>) {
        detail.clear()
        detail.addAll(newDetail)
        notifyDataSetChanged()
    }
}