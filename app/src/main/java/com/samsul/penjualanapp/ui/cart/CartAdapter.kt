package com.samsul.penjualanapp.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samsul.penjualanapp.R
import com.samsul.penjualanapp.data.model.cart.DataCart
import com.samsul.penjualanapp.utils.GlideHelper
import kotlinx.android.synthetic.main.adapter_cart.view.*
import kotlinx.android.synthetic.main.adapter_cart.view.tvCategory
import kotlinx.android.synthetic.main.adapter_cart.view.tvNameProduct
import kotlinx.android.synthetic.main.adapter_cart.view.tvPrice
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CartAdapter(val mContext : Context, val cart : ArrayList<DataCart>,
                  val clickListener : (DataCart, Int) -> Unit) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        val view = view
        fun bind(cart: DataCart){
            view.tvCategory.text = cart.kategori
            view.tvNameProduct.text = cart.nama_produk
            view.tvPrice.text = "${cart.harga_rupiah} x ${cart.jumlah}"

            val total : Double = cart.jumlah!!.toDouble() * cart.harga!!.toDouble()
            val totalRupiah = NumberFormat.getInstance(Locale.GERMANY).format(total)

            view.tvTotalHasil.text = "Rp $totalRupiah"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.adapter_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cart[position])
//        Glide.with(mContext)
//            .load(dataAgent[position].gambarToko)
//            .centerCrop()
//            .placeholder(R.drawable.img_no_image)
//            .error(R.drawable.img_no_image)
//            .into(holder.view.imvImage)
        GlideHelper.setImage(mContext, cart[position].gambar_produk!!, holder.view.imvImageCart)
        holder.view.imvDelete.setOnClickListener {
            clickListener(cart[position], position)
            removeCart(position)
        }
    }

    override fun getItemCount(): Int = cart.size

    fun setData(newCart : List<DataCart>) {
        cart.clear()
        cart.addAll(newCart)
        notifyDataSetChanged()
    }
    fun removeAgent(position: Int){
        cart.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, cart.size)
    }

    fun removeCart(position : Int) {
        cart.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, cart.size)
    }

    fun removeAll(){
        cart.clear()
        notifyDataSetChanged()
    }

}