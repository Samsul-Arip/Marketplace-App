package com.samsul.penjualanapp.ui.agent.update

import com.samsul.penjualanapp.data.model.agent.ResponseAgentDetail
import com.samsul.penjualanapp.data.model.agent.ResponseAgentUpdate
import com.samsul.penjualanapp.network.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class AgentUpdatePresenter(val view : AgentUpdateContract.View) : AgentUpdateContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun getDetail(kd_agen: Long) {
        view.onLoading(true)
        ApiService.endPoint.getAgentDetail(kd_agen).enqueue(object : Callback<ResponseAgentDetail> {
            override fun onResponse(call: Call<ResponseAgentDetail>, response: Response<ResponseAgentDetail>) {
                view.onLoading(false)
                if(response.isSuccessful) {
                    val responseAgentDetail : ResponseAgentDetail? = response.body()
                    view.onResultDetail(responseAgentDetail!!)
                }
            }

            override fun onFailure(call: Call<ResponseAgentDetail>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

    override fun updateAgent(kd_agen: Long, nama_toko: String, nama_pemilik: String, alamat: String, latitude: String, longitude: String, gambar_toko: File?) {
        val requestBody: RequestBody
        val multipartBody : MultipartBody.Part
        if(gambar_toko != null) {
            requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), gambar_toko)
            multipartBody = MultipartBody.Part.createFormData("gambar_toko", gambar_toko.name, requestBody)
        } else {
            requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), "")
            multipartBody = MultipartBody.Part.createFormData("gambar_toko", "", requestBody)
        }

        view.onLoading(true)
        ApiService.endPoint.updateAgent(kd_agen,nama_toko,nama_pemilik,alamat,latitude,longitude,multipartBody,"PUT")
                .enqueue(object : Callback<ResponseAgentUpdate> {
                    override fun onResponse(call: Call<ResponseAgentUpdate>, response: Response<ResponseAgentUpdate>) {
                        view.onLoading(false)
                        if(response.isSuccessful) {
                            val responseAgentUpdate : ResponseAgentUpdate? = response.body()
                            view.onResultUpdate(responseAgentUpdate!!)
                        }
                    }

                    override fun onFailure(call: Call<ResponseAgentUpdate>, t: Throwable) {
                        view.onLoading(false)

                    }

                })

    }

}