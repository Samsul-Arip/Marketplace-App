package com.samsul.penjualanapp.ui.agent.create

import com.samsul.penjualanapp.data.model.agent.ResponseAgentUpdate
import com.samsul.penjualanapp.network.ApiService
import okhttp3.MediaType
import okhttp3.MediaType.Companion.parse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class AgentCreatePresenter(val view : AgentCreateContract.View) : AgentCreateContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }


    override fun insertAgent(nama_toko: String, namaPemilik: String, alamat: String, latitude: String, longitude: String, gambar_toko: File) {
        val requestBody : RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(),gambar_toko)
        val multiPartBody : MultipartBody.Part? = MultipartBody.Part.createFormData("gambar_toko", gambar_toko.name, requestBody)
        view.onLoading(true)
        ApiService.endPoint.insertAgent(nama_toko,namaPemilik,alamat,latitude,longitude,multiPartBody!!)
                .enqueue(object : Callback<ResponseAgentUpdate>{
                    override fun onResponse(call: Call<ResponseAgentUpdate>, response: Response<ResponseAgentUpdate>) {
                        view.onLoading(false)
                        if(response.isSuccessful) {
                            val responseAgentUpdate :ResponseAgentUpdate? = response.body()
                            view.onResult(responseAgentUpdate!!)
                        }
                    }

                    override fun onFailure(call: Call<ResponseAgentUpdate>, t: Throwable) {
                        view.onLoading(false)
                    }

                })
    }
}