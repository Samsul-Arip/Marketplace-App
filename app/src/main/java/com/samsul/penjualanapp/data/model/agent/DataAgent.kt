package com.samsul.penjualanapp.data.model.agent

import com.google.gson.annotations.SerializedName

data class DataAgent(

    @SerializedName("kd_agen") val kdAgen : Long?,
    @SerializedName("nama_toko") val namaToko : String?,
    @SerializedName("nama_pemilik") val namaPemilik : String?,
    @SerializedName("alamat") val alamat : String?,
    @SerializedName("latitude") val latitude : String?,
    @SerializedName("longitude") val longitude : String?,
    @SerializedName("gambar_toko") val gambarToko : String?,

)
