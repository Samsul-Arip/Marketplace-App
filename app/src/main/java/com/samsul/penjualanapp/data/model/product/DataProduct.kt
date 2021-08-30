package com.samsul.penjualanapp.data.model.product

import com.google.gson.annotations.SerializedName

data class DataProduct(
    @SerializedName("kd_produk") val kdProduk : Long?,
    @SerializedName("kd_kategori") val kdKategori : String?,
    @SerializedName("nama_produk") val nama_produk : String?,
    @SerializedName("harga") val harga : String?,
    @SerializedName("harga_rupiah") val harga_rupiah : String?,
    @SerializedName("gambar_produk") val gambar_produk : String?,
    @SerializedName("stok") val stok : String?,
    @SerializedName("kategori") val kategori : String?,
)
