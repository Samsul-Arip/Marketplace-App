package com.samsul.penjualanapp.ui.agent.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.samsul.penjualanapp.R
import com.samsul.penjualanapp.data.Constant
import com.samsul.penjualanapp.data.model.agent.DataAgent
import com.samsul.penjualanapp.utils.GlideHelper
import kotlinx.android.synthetic.main.adapter_agent_search.view.*

class AgentSearchAdapter(val mContext : Context, val dataAgent : ArrayList<DataAgent>,
                         val clickListener : (DataAgent, Int) -> Unit) :
    RecyclerView.Adapter<AgentSearchAdapter.ViewHolder>() {

    class ViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        val view = view
        fun bind(dataAgent: DataAgent){
            view.txvNameStore.text = dataAgent.namaToko
            view.txvLocation.text = dataAgent.alamat
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.adapter_agent_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataAgent[position])
        GlideHelper.setImage(mContext, dataAgent[position].gambarToko!!, holder.view.imvImage)

        holder.view.crvAgent.setOnClickListener {
            Constant.AGENT_ID = dataAgent[position].kdAgen!!
            clickListener(dataAgent[position], position)
        }
//        Glide.with(mContext)
//            .load(dataAgent[position].gambarToko)
//            .centerCrop()
//            .placeholder(R.drawable.img_no_image)
//            .error(R.drawable.img_no_image)
//            .into(holder.view.imvImage)
    }

    override fun getItemCount(): Int = dataAgent.size

    fun setData(newDataAgent : List<DataAgent>) {
        dataAgent.clear()
        dataAgent.addAll(newDataAgent)
        notifyDataSetChanged()
    }


}