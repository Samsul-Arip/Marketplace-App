package com.samsul.penjualanapp.ui.agent.search

import com.samsul.penjualanapp.data.model.agent.ResponseAgentList
import com.samsul.penjualanapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgentSearchPresenter(val view : AgentSearchContract.View) : AgentSearchContract.Presenter {

    init {
        view.initListener()
        view.initActivity()
    }

    override fun getAgent() {
        view.onLoading(true)
        ApiService.endPoint.getAgent().enqueue(object : Callback<ResponseAgentList> {
            override fun onResponse(
                call: Call<ResponseAgentList>,
                response: Response<ResponseAgentList>
            ) {
                view.onLoading(false)
                if(response.isSuccessful) {
                    val responseAgentList : ResponseAgentList? = response.body()
                    view.onResultAgent( responseAgentList!! )
                }
            }

            override fun onFailure(call: Call<ResponseAgentList>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

    override fun searcAgent(keyword: String) {
        view.onLoading(true)
        ApiService.endPoint.searchAgent(keyword).enqueue(object : Callback<ResponseAgentList>{
            override fun onResponse(
                call: Call<ResponseAgentList>,
                response: Response<ResponseAgentList>
            ) {
                view.onLoading(false)
                if(response.isSuccessful) {
                    val responseAgentList : ResponseAgentList? = response.body()
                    view.onResultAgent(responseAgentList!!)
                }
            }

            override fun onFailure(call: Call<ResponseAgentList>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

}