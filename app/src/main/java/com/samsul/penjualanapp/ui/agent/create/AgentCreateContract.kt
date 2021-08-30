package com.samsul.penjualanapp.ui.agent.create

import com.samsul.penjualanapp.data.model.agent.ResponseAgentUpdate
import java.io.File

interface AgentCreateContract {

    interface Presenter {
        fun insertAgent(nama_toko : String, namaPemilik : String, alamat:String, latitude : String, longitude : String, gambar_toko:File)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading : Boolean)
        fun onResult(responseAgentUpdate: ResponseAgentUpdate)
        fun showMessage(message:String)
    }
}