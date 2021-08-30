package com.samsul.penjualanapp.ui.agent.search

import com.samsul.penjualanapp.data.model.agent.ResponseAgentList

interface AgentSearchContract {

    interface Presenter {
        fun getAgent()
        fun searcAgent(keyword : String)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading : Boolean)
        fun onResultAgent(responseAgentList: ResponseAgentList)
    }
}