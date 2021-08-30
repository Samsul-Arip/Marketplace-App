package com.samsul.penjualanapp.ui.agent

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
import kotlinx.android.synthetic.main.adapter_agent.view.*

class AgentAdapter(val mContext : Context, val dataAgent : ArrayList<DataAgent>,
                   val clickListener : (DataAgent, Int, String) -> Unit) :
    RecyclerView.Adapter<AgentAdapter.ViewHolder>() {

    class ViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        val view = view
        fun bind(dataAgent: DataAgent){
            view.tvNameStore.text = dataAgent.namaToko
            view.tvLocation.text = dataAgent.alamat
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.adapter_agent, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataAgent[position])
        GlideHelper.setImage(mContext, dataAgent[position].gambarToko!!, holder.view.imvImage)

        holder.view.imvImage.setOnClickListener {
            Constant.AGENT_ID = dataAgent[position].kdAgen!!
            clickListener(dataAgent[position], position, "detail")
        }
//        Glide.with(mContext)
//            .load(dataAgent[position].gambarToko)
//            .centerCrop()
//            .placeholder(R.drawable.img_no_image)
//            .error(R.drawable.img_no_image)
//            .into(holder.view.imvImage)
        holder.view.tvOptions.setOnClickListener {
            val popupMenu =  PopupMenu(mContext,holder.view.tvOptions)
            popupMenu.inflate(R.menu.menu_options)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.action_update -> {
                        Constant.AGENT_ID = dataAgent[position].kdAgen!!
                        clickListener(dataAgent[position], position, "update")
                    }

                    R.id.action_delete -> {
                        Constant.AGENT_ID = dataAgent[position].kdAgen!!
                        clickListener(dataAgent[position], position, "delete")
                    }
                }
                true
            }
            popupMenu.show()
        }
    }

    override fun getItemCount(): Int = dataAgent.size

    fun setData(newDataAgent : List<DataAgent>) {
        dataAgent.clear()
        dataAgent.addAll(newDataAgent)
        notifyDataSetChanged()
    }
    fun removeAgent(position: Int){
        dataAgent.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, dataAgent.size)
    }

}