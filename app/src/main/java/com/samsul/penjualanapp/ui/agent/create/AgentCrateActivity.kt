package com.samsul.penjualanapp.ui.agent.create

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.samsul.penjualanapp.R
import com.samsul.penjualanapp.data.Constant
import com.samsul.penjualanapp.data.model.agent.ResponseAgentUpdate
import com.samsul.penjualanapp.ui.agent.AgentMapsActivity
import com.samsul.penjualanapp.utils.FileUtils
import com.samsul.penjualanapp.utils.GaleryHelper
import kotlinx.android.synthetic.main.activity_agent_crate.*

class AgentCrateActivity : AppCompatActivity(),AgentCreateContract.View {

    private var uriImage : Uri? = null
    private var pickImage = 1
    lateinit var presenter: AgentCreatePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_crate)


        presenter = AgentCreatePresenter(this)

    }

    override fun onStart() {
        super.onStart()
        if(Constant.LATITUDE.isNotEmpty()){
            edtLocation.setText("${Constant.LATITUDE}, ${Constant.LONGITUDE}")

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Constant.LATITUDE =""
        Constant.LONGITUDE = ""
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == pickImage && resultCode == Activity.RESULT_OK) {
            uriImage = data!!.data
            imvImage.setImageURI(uriImage)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun initActivity() {
        supportActionBar!!.title = "Tambah Agen"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initListener() {
        edtLocation.setOnClickListener {
            startActivity(Intent(this, AgentMapsActivity::class.java))
        }

        imvImage.setOnClickListener {
            if(GaleryHelper.permissionGalerry(this,this, pickImage)) {
                GaleryHelper.openGalerry(this)
            }
        }
        btnSimpan.setOnClickListener {
            val nameStore = edtNameStore.text
            val ownerStore = edtOwnerStore.text
            val alamat = edtAlamat.text
            val location = edtLocation.text

            if(nameStore.isNullOrEmpty() || ownerStore.isNullOrEmpty() || alamat.isNullOrEmpty() || location.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "Masukkan Data yang benar", Toast.LENGTH_SHORT).show()
            } else {
                presenter.insertAgent(nameStore.toString(), ownerStore.toString(),
                        alamat.toString(), Constant.LATITUDE, Constant.LONGITUDE, FileUtils.getFile(this, uriImage))
            }
        }
    }

    override fun onLoading(loading: Boolean) {
        when(loading) {
            true -> {
                proggressBar.visibility = View.VISIBLE
                btnSimpan.visibility = View.GONE
            }
            false -> {
                proggressBar.visibility = View.GONE
                btnSimpan.visibility = View.VISIBLE
            }
        }
    }

    override fun onResult(responseAgentUpdate: ResponseAgentUpdate) {
        showMessage(responseAgentUpdate.msg)
        finish()
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}