package com.example.scanner_kotlin

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import com.example.scanner_kotlin.databinding.ActivityScanQrBinding
import com.google.zxing.integration.android.IntentIntegrator


class ScanQr : AppCompatActivity() {
    lateinit var dataBinding : ActivityScanQrBinding
    var flag = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_scan_qr)
        dataBinding.scanBn.setOnClickListener(View.OnClickListener {
            flag = 1
            val intentIntegrator = IntentIntegrator(this)
            intentIntegrator.setPrompt("for flash, use value up")
            intentIntegrator.setBeepEnabled(true)
            intentIntegrator.setOrientationLocked(true)
            intentIntegrator.initiateScan()
        })

        dataBinding.copyBn.setOnClickListener(View.OnClickListener {
            if (flag == 1){
                val  clipBord = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("Edit text", dataBinding.scannedTextTv.text.toString())
                clipBord.setPrimaryClip(clipData)
                Toast.makeText(this, "data copied", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Nothing copied", Toast.LENGTH_SHORT).show()
            }
        })


    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (intentResult.contents != null) {
            dataBinding.scannedTextTv.text = intentResult.contents
        } else {
            Toast.makeText(this, "Oops... something went to wong", Toast.LENGTH_SHORT).show()
        }
    }
}