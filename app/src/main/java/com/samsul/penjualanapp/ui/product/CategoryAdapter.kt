package com.samsul.penjualanapp.ui.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samsul.penjualanapp.R
import com.samsul.penjualanapp.data.Constant
import com.samsul.penjualanapp.data.model.product.DataCategory
import com.samsul.penjualanapp.data.model.transaction.detail.DataDetail
import com.samsul.penjualanapp.utils.GlideHelper
import kotlinx.android.synthetic.main.adapter_category.view.*
import kotlinx.android.synthetic.main.adapter_transaction_detail.view.*
import kotlinx.android.synthetic.main.adapter_transaction_detail.view.imvImage
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CategoryAdapter(val mContext : Context, var category : ArrayList<DataCategory>, val clickListener : (DataCategory, Int) -> Unit) :
        RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val view  = view
        fun bind(category: DataCategory){
            view.txvCategory.text = category.kategori
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.adapter_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bind(category[position])
        GlideHelper.setImage(mContext, category[position].gambar_kategori!!,holder.view.imvImage)
        holder.view.relCategory.setOnClickListener {
            Constant.CATEGORY_ID = category[position].kd_kategori!!
            clickListener(category[position], position)
        }
    }

    override fun getItemCount(): Int = category.size

    fun setData(newCategory : List<DataCategory>) {
        category.clear()
        category.addAll(newCategory)
        notifyDataSetChanged()
    }
}