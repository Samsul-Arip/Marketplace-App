package com.samsul.penjualanapp.ui.agent.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.samsul.penjualanapp.R
import com.samsul.penjualanapp.data.Constant
import com.samsul.penjualanapp.data.model.agent.DataAgent
import com.samsul.penjualanapp.data.model.agent.ResponseAgentList
import kotlinx.android.synthetic.main.activity_agent_search.*

class AgentSearchActivity : AppCompatActivity(), AgentSearchContract.View {

    private lateinit var agentSearchAdapter: AgentSearchAdapter
    private lateinit var agentSearchPresenter: AgentSearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_search)
        agentSearchPresenter = AgentSearchPresenter(this)
        agentSearchPresenter.getAgent()
    }

    override fun initActivity() {
        supportActionBar!!.title = "Pilih Agen"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initListener() {
        agentSearchAdapter = AgentSearchAdapter(this, arrayListOf()) {
            dataAgent: DataAgent, position: Int ->
            Constant.AGENT_ID = dataAgent.kdAgen!!
            Constant.AGENT_NAME = dataAgent.namaToko!!
            Constant.IS_CHANGE = true
            finish()
        }
        edtSearch.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                agentSearchPresenter.searcAgent(edtSearch.text.toString())
                true
            } else {
                false
            }
        }

        rcvAgent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = agentSearchAdapter
        }
        swipe.setOnRefreshListener {
            agentSearchPresenter.getAgent()
        }
    }

    override fun onLoading(loading: Boolean) {
        when(loading) {
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false
        }
    }

    override fun onResultAgent(responseAgentList: ResponseAgentList) {
        val dataAgent : List<DataAgent> = responseAgentList.dataAgent
        agentSearchAdapter.setData(dataAgent)
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return super.onNavigateUp()
    }
}