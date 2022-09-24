package com.example.scanner_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<LinearLayout>(R.id.generateQR).setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, GenerateQr ::class.java))
        })
        findViewById<LinearLayout>(R.id.scanQrcode).setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, ScanQr ::class.java))
        })
    }
}