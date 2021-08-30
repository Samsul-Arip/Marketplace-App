package com.samsul.penjualanapp.ui.transaction

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samsul.penjualanapp.R
import com.samsul.penjualanapp.data.model.transaction.DataTransaction
import kotlinx.android.synthetic.main.adapter_transaction.view.*

class TransactionAdapter(val mContext : Context, val transaction : ArrayList<DataTransaction>,
                         val clickListener : (DataTransaction, Int) -> Unit) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    class ViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        val view = view
        fun bind(transaction: DataTransaction){
            view.tvTotal.text = transaction.total_rupiah
            view.tvInvoice.text = transaction.no_faktur
            view.tvDate.text = transaction.tgl_penjualan
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.adapter_transaction, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(transaction[position])
//        Glide.with(mContext)
//            .load(dataAgent[position].gambarToko)
//            .centerCrop()
//            .placeholder(R.drawable.img_no_image)
//            .error(R.drawable.img_no_image)
//            .into(holder.view.imvImage)
        holder.view.tvSee.setOnClickListener {
           clickListener(transaction[position], position)
        }
    }

    override fun getItemCount(): Int = transaction.size

    fun setData(newTransaction : List<DataTransaction>) {
        transaction.clear()
        transaction.addAll(newTransaction)
        notifyDataSetChanged()
    }
    fun removeAgent(position: Int){
        transaction.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, transaction.size)
    }

}