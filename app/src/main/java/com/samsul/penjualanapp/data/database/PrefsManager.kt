package com.samsul.penjualanapp.data.database

import android.content.Context
import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.booleanPref
import hu.autsoft.krate.stringPref

class PrefsManager (val context : Context) : Krate {

    private val PREFS_IS_LOGIN : String = "prefs_is_login"
    private val PREFS_IS_USERNAME : String = "prefs_is_username"
    private val PREFS_IS_PASSWORD : String = "prefs_password"
    private val PREFS_IS_NAMA_PEGAWAI : String = "prefs_pegawai"
    private val PREFS_IS_JK : String = "prefs_jk"
    private val PREFS_IS_ALAMAT : String = "prefs_alamat"
    private val PREFS_IS_AKTIF : String = "prefs_is_aktif"

    override val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.applicationContext.getSharedPreferences(
            "db_penjualan", Context.MODE_PRIVATE
        )
    }

    var prefsIsLogin by booleanPref(PREFS_IS_LOGIN, false)
    var prefsIsUsername by stringPref(PREFS_IS_USERNAME, "")
    var prefsIsPassword by stringPref(PREFS_IS_PASSWORD, "")
    var prefsIsNamaPegawai by stringPref(PREFS_IS_NAMA_PEGAWAI, "")
    var prefsIsJk by stringPref(PREFS_IS_JK, "")
    var prefsIsAlamat by stringPref(PREFS_IS_ALAMAT, "")
    var prefsIsAktf by stringPref(PREFS_IS_AKTIF, "")

    fun logOut(){
        sharedPreferences.edit().clear().apply()
    }

}